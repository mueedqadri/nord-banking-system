package com.nord.persistence.bankingTransactions;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;
import com.nord.persistence.bankingTransactions.interfaces.IMakeCustomerTransactions;
import com.nord.service.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for updating and fetching withdraw and spending limits
 * @author Jay Nimeshkumar Patel
 */
public class MakeCustomerTransactions extends AbstractBanking implements IMakeCustomerTransactions {

  private Connection dbConnection;
  private PreparedStatement prepareStatement;
  private ResultSet resultSet;

  @Override
  public Map<String, Double> getWithdrawalSpendingLimit() {
    Map<String, Double> limitValues = new HashMap<>();
    try {
      dbConnection = DbConnection.getConnection();
      prepareStatement = dbConnection.prepareStatement("SELECT withdraw_limit, spending_limit FROM customer_details where user_id = ?;");
      prepareStatement.setInt(1, Context.getLoggedInUserId());
      resultSet = prepareStatement.executeQuery();
      if (resultSet.next()) {
        limitValues.put(ColumnNames.WITHDRAW, resultSet.getDouble(ColumnNames.WITHDRAW));
        limitValues.put(ColumnNames.SPENDING, resultSet.getDouble(ColumnNames.SPENDING));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        prepareStatement.close();
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return limitValues;
  }

  @Override
  public boolean setDbWithdrawalSpendingLimit(String limitType, double updatedAmount) {
    try {
      dbConnection = DbConnection.getConnection();
      prepareStatement = dbConnection.prepareStatement("UPDATE customer_details SET " + limitType + " = ? WHERE user_id = ?;");
      prepareStatement.setDouble(1, updatedAmount);
      prepareStatement.setInt(2, Context.getLoggedInUserId());
      int result = prepareStatement.executeUpdate();
      if (result == 1) {
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      try {
        prepareStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return false;
  }
}
