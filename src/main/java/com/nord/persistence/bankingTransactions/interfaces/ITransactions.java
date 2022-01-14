package com.nord.persistence.bankingTransactions.interfaces;


/**
 * @author Jay Nimeshkumar Patel
 */
public interface ITransactions {

  /**
   * This method is responsible for updating the account balance and payee details after any transaction
   * @return true on the successful of updating of the transaction details
   */
  boolean saveCustomerTransaction(ICustomerTransactionsModel customerTransactionsModel);
}
