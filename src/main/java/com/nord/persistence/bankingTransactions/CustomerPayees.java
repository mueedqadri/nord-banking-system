package com.nord.persistence.bankingTransactions;

import com.nord.common.ColumnNames;
import com.nord.common.Constants;
import com.nord.persistence.DbConnection;
import com.nord.persistence.bankingTransactions.interfaces.ICustomerPayees;
import com.nord.persistence.bankingTransactions.interfaces.IPayeeDetailsModel;
import com.nord.service.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class provides methods for all payee related operations from the database
 * @author Rahul Reddy Puchakayala
 */
public class CustomerPayees implements ICustomerPayees {

  private Connection dbConnection;
  private PreparedStatement preparedStatement;
  private ResultSet resultSet;

  public List<IPayeeDetailsModel> getPayeeDetails() {
    List<IPayeeDetailsModel> payeeList = new ArrayList<>();
    try {
      dbConnection = DbConnection.getConnection();
      preparedStatement = dbConnection.prepareStatement("SELECT * FROM payee_details WHERE user_id= ? ORDER BY 1 ASC");
      preparedStatement.setInt(1, Context.getLoggedInUserId());
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        IPayeeDetailsModel payeeDetails = new PayeeDetailsModel();
        payeeDetails.setPayeeId(resultSet.getInt(ColumnNames.PAYEE_ID));
        payeeDetails.setFirstName(resultSet.getString(ColumnNames.FIRST_NAME));
        payeeDetails.setUserId(Context.getLoggedInUserId());
        payeeDetails.setLastName(resultSet.getString(ColumnNames.LAST_NAME));
        payeeDetails.setAccountNo(resultSet.getLong(ColumnNames.ACCOUNT_NO));
        payeeDetails.setNickName(resultSet.getString(ColumnNames.NICK_NAME));
        payeeList.add(payeeDetails);
      }
      return payeeList;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        preparedStatement.close();
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return payeeList;
  }

  @Override
  public boolean addPayee(Map<String, Object> payeeDetails) {
    try {
      dbConnection = DbConnection.getConnection();
      preparedStatement = dbConnection.prepareStatement("INSERT INTO payee_details (user_id, firstname, lastname, account_no,nickname) VALUES (?,?,?,?,?)");
      preparedStatement.setInt(1, Context.getLoggedInUserId());
      preparedStatement.setString(2, payeeDetails.get(Constants.FIRST_NAME).toString());
      preparedStatement.setString(3, payeeDetails.get(Constants.LAST_NAME).toString());
      preparedStatement.setLong(4, Long.parseLong(payeeDetails.get(Constants.ACCOUNT_NO_VALID).toString()));
      preparedStatement.setString(5, payeeDetails.get(Constants.NICK_NAME).toString());
      preparedStatement.execute();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public boolean deletePayee(int payeeIndex) {
    try {
      dbConnection = DbConnection.getConnection();
      preparedStatement = dbConnection.prepareStatement("DELETE FROM payee_details WHERE payee_id= ?;");
      preparedStatement.setInt(1, payeeIndex);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public boolean editPayeeDetails(int payeeIndex, Object newValue, String type) {
    try {
      dbConnection = DbConnection.getConnection();
      preparedStatement = dbConnection.prepareStatement("UPDATE payee_details SET " + type + " =? WHERE  payee_id=?;");
      if (type.equals(ColumnNames.ACCOUNT_NO)) {
        preparedStatement.setLong(1, Long.parseLong(newValue.toString()));
      } else {
        preparedStatement.setString(1, (String) newValue);
      }
      preparedStatement.setInt(2, payeeIndex);
      int result = preparedStatement.executeUpdate();
      if (result == 1) {
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return false;
  }
}
