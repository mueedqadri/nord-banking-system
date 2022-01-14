package com.nord.service.bankingTransactions.interfaces;

/**
 * This interface is implemented by TransactionService
 * @author Jay Nimeshkumar Patel
 */
public interface ITransaction {

  /**
   * This method is responsible for fetching the current account balance
   */
  void balanceEnquiry();

  /**
   * This method updates withdraw/spending Limits
   */
  void setWithdrawalLimit();

  /**
   * This method is responsible for updating the account balance after any transaction
   */
  void makeTransaction();
}
