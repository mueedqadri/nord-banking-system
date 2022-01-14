package com.nord.service.bankingTransactions;

import com.nord.persistence.bankingTransactions.MakeCustomerTransactions;
import com.nord.persistence.bankingTransactions.interfaces.IMakeCustomerTransactions;
import com.nord.service.Context;

/**
 * Mock class for MakeTransactons persistence class
 */
public class TransactionsMock {

  public CharSequence getCustomerBalanceMock() {
    Context.setLoggedInUserId(1);
    IMakeCustomerTransactions banking = new MakeCustomerTransactions();
    return String.valueOf(banking.getCustomerBalance());
  }

  public String getWithdrawalSpendingLimitMock() {
    String limitValues = "Withdraw/Spending Limit can't exceed 10,000 CAD";
    return limitValues;
  }

  public String makeTransactionMock() {
    String validate = "Make Transaction";
    return validate;
  }
}
