package com.nord.service.transactionHistory.interfaces;

import com.nord.persistence.transactionHistory.interfaces.ICustomerTransactionsHistory;

/**
 * Interface which is implemented by transactionHistoryFactory class
 * @author Rahul Reddy Puchakayala
 */
public interface ITransactionHistoryFactory {

  /**
   * It creates the object for TransactionHistory persistence class
   * @return object of TransactionHistory
   */
  ICustomerTransactionsHistory getDbTransactions();

  /**
   * Retrieves the last five transactions of current user using builder pattern
   * @return object of getAllFiveTransactions
   */
  IGetPreviousTransactions showLastFiveTransactions();

  /**
   * creates the object for getAllDatePeriodTransactions service class
   * @return the object of getAllDatePeriodTransactions
   */
  IGetPreviousTransactions showDatePeriodTransactions();

  /**
   * creates the object for printTransactionStatement service class
   * @return the object of printTransactionStatement
   */
  IGetPreviousTransactions printTransactionStatement();
}
