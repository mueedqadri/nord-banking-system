package com.nord.persistence.transactionHistory;

import com.nord.common.ColumnNames;
import com.nord.persistence.bankingTransactions.CustomerTransactionsModel;
import com.nord.persistence.DbConnection;
import com.nord.persistence.transactionHistory.interfaces.ICustomerTransactionsHistory;
import com.nord.persistence.bankingTransactions.interfaces.ICustomerTransactionsModel;
import com.nord.service.Context;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for showing customers' previous transactions and
 * printing E-statements
 * @author Rahul Reddy Puchakayala
 */
public class CustomerTransactionsHistory implements ICustomerTransactionsHistory {

  private Connection dbConnection;
  private PreparedStatement preparedStatement;
  private ResultSet resultSet;

  @Override
  public List<ICustomerTransactionsModel> getLastFiveTransactions() {
    List<ICustomerTransactionsModel> transactionsList = new ArrayList<>();
    try {
      dbConnection = DbConnection.getConnection();
      preparedStatement = dbConnection.prepareStatement("SELECT * from " +
              "customer_transactions WHERE user_id = ? ORDER BY 1 DESC LIMIT " +
              "5;");
      preparedStatement.setInt(1, Context.getLoggedInUserId());
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        CustomerTransactionsModel customerTransactions = new CustomerTransactionsModel();
        customerTransactions.setTransactionId(resultSet.getInt(ColumnNames.TRANSACTION_ID));
        customerTransactions.setUserId(resultSet.getInt(ColumnNames.USER_ID));
        customerTransactions.setComment(resultSet.getString(ColumnNames.COMMENT));
        customerTransactions.setAmount(resultSet.getDouble(ColumnNames.AMOUNT));
        customerTransactions.setTransactionDate(resultSet.getDate(ColumnNames.TRANSACTION_DATE));
        customerTransactions.setTransactionType(resultSet.getString(ColumnNames.TRANSACTION_TYPE));
        transactionsList.add(customerTransactions);
      }
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
    return transactionsList;
  }

  @Override
  public List<ICustomerTransactionsModel> getDatePeriodTransactions(Date fromDateDb, Date toDateDb) {
    List<ICustomerTransactionsModel> transactionsList = new ArrayList<>();
    try {
      dbConnection = DbConnection.getConnection();
      preparedStatement = dbConnection.prepareStatement("SELECT * from customer_transactions ct left join customer_details cd on cd.user_id=ct.user_id where ct.user_id=? and ct.transaction_date between ? and ? ORDER BY 7 DESC;");
      preparedStatement.setInt(1, Context.getLoggedInUserId());
      preparedStatement.setDate(2, fromDateDb);
      preparedStatement.setDate(3, toDateDb);

      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        CustomerTransactionsModel customerTransactions = new CustomerTransactionsModel();
        customerTransactions.setTransactionId(resultSet.getInt(ColumnNames.TRANSACTION_ID));
        customerTransactions.setUserId(resultSet.getInt(ColumnNames.USER_ID));
        customerTransactions.setComment(resultSet.getString(ColumnNames.COMMENT));
        customerTransactions.setTransactionDate(resultSet.getDate(ColumnNames.TRANSACTION_DATE));
        customerTransactions.setTransactionType(resultSet.getString(ColumnNames.TRANSACTION_TYPE));
        customerTransactions.setAmount(resultSet.getDouble(ColumnNames.AMOUNT));
        transactionsList.add(customerTransactions);
      }
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
    return transactionsList;
  }

  @Override
  public void closeDbConnection() {
    DbConnection.closeDbConnection();
  }
}
