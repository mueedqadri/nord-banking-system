package com.nord.persistence.bankingTransactions.interfaces;

import java.util.Map;

/**
 * This interface is implemented by MakeCustomerTransaction class
 * @author Jay Nimeshkumar Patel
 */
public interface IMakeCustomerTransactions {

  double getCustomerBalance();

  Map<String, Double> getWithdrawalSpendingLimit();

  /**
   * This method updates withdraw/spending Limits
   * @return returns true if the limits are successfully updated false otherwise
   */
  boolean setDbWithdrawalSpendingLimit(String limitType, double updatedAmount);

  /**
   * This method is responsible for updating the account balance after any transaction
   * @return True if details gets successfully updated false otherwise
   */
  boolean makeNewTransaction(double transferAmount, String isWithdrawal, String comment);

}
