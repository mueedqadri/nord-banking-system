package com.nord.persistence.bankingTransactions;

import com.nord.persistence.DbConnection;
import com.nord.persistence.bankingTransactions.interfaces.ICustomerTransactionsModel;
import com.nord.persistence.bankingTransactions.interfaces.ITransactions;
import com.nord.persistence.notification.AbstractNotificationDb;
import com.nord.service.Context;

import java.sql.*;
import java.time.LocalDate;

/**
 * This class is responsible for updating transaction details
 * @author Jay Nimeshkumar Patel
 */
public abstract class AbstractTransactions extends AbstractNotificationDb implements ITransactions {

  private PreparedStatement preparedStatement;

  @Override
  public boolean saveCustomerTransaction(ICustomerTransactionsModel customerTransactionsModel) {
    boolean result = false;
    try {
      Connection dbConnection = DbConnection.getConnection();
      preparedStatement = dbConnection.prepareStatement("INSERT INTO customer_transactions (user_id, comment, amount,transaction_type,transaction_date) VALUES (?,?,?,?,?)");
      preparedStatement.setInt(1, Context.getLoggedInUserId());
      preparedStatement.setString(2, customerTransactionsModel.getComment());
      preparedStatement.setDouble(3, customerTransactionsModel.getAmount());
      preparedStatement.setString(4, customerTransactionsModel.getTransactionType());
      preparedStatement.setDate(5, Date.valueOf(LocalDate.now()));
      result = preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }
}
