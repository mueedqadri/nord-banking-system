package com.nord.persistence.bankingTransactions;

import com.nord.common.ColumnNames;
import com.nord.common.Constants;
import com.nord.persistence.DbConnection;
import com.nord.persistence.bankingTransactions.interfaces.IBanking;
import com.nord.persistence.bankingTransactions.interfaces.ICustomerTransactionsModel;
import com.nord.service.Context;

import java.sql.*;
import java.time.LocalDate;

/**
 * This class is responsible for updating new balance and fetching the current account balance
 * @author Jay Nimeshkumar Patel
 */
public abstract class AbstractBanking extends AbstractTransactions implements IBanking {

  private PreparedStatement prepareStatement;

  @Override
  public boolean makeNewTransaction(double transferAmount, String operator, String comment) {
    try {
      Connection dbConnection = DbConnection.getConnection();
      prepareStatement = dbConnection.prepareStatement("UPDATE customer_details SET account_balance = account_balance " + operator + " ? WHERE user_id = ?;");
      prepareStatement.setDouble(1, transferAmount);
      prepareStatement.setInt(2, Context.getLoggedInUserId());
      prepareStatement.executeUpdate();

      ICustomerTransactionsModel model = new CustomerTransactionsModel();
      model.setComment(comment);
      model.setAmount(transferAmount);
      String transactionType = operator.equals(Constants.SUBTRACT) ? Constants.DEBIT : Constants.CREDIT;
      model.setTransactionType(transactionType);
      model.setTransactionDate(Date.valueOf(LocalDate.now()));
      saveCustomerTransaction(model);
      return true;

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
  }

  public double getCustomerBalance() {
    try {
      Connection dbConnection = DbConnection.getConnection();
      prepareStatement = dbConnection.prepareStatement("SELECT account_balance FROM customer_details where user_id = ?;");
      prepareStatement.setInt(1, Context.getLoggedInUserId());
      ResultSet resultSet = prepareStatement.executeQuery();
      if (resultSet.next()) {
        return resultSet.getDouble(ColumnNames.ACCOUNT_BALANCE);
      }
      resultSet.close();
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    } finally {
      try {
        prepareStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return -1;
  }
}
