package com.nord.persistence.transactionHistory.interfaces;

import com.nord.persistence.bankingTransactions.interfaces.ICustomerTransactionsModel;

import java.sql.Date;
import java.util.List;

/**
 * This interface is implemented by CustomerTransactionHistory class
 * @author Rahul Reddy Puchakayala, Jay Nimeshkumar Patel
 */
public interface ICustomerTransactionsHistory {

  List<ICustomerTransactionsModel> getLastFiveTransactions();

  List<ICustomerTransactionsModel> getDatePeriodTransactions(Date fromDateDb, Date toDateDb);

  void closeDbConnection();
}
