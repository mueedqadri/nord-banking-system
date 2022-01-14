package com.nord.persistence.bankingTransactions.interfaces;

/**
 * @author Jay Nimeshkumar Patel
 */
public interface IBanking {

  /**
   * This method is responsible for updating the account balance after any transaction
   * @return True if details gets successfully updated false otherwise
   */
  boolean makeNewTransaction(double transferAmount, String operator, String comment);

  /**
   * This method is responsible for fetching the current account balance
   * @return returns customer balance
   */
  double getCustomerBalance();
}
