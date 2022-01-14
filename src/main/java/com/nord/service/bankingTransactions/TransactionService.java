package com.nord.service.bankingTransactions;

import com.nord.common.ColumnNames;
import com.nord.common.Constants;
import com.nord.persistence.bankingTransactions.interfaces.IPayeeDetailsModel;
import com.nord.service.bankingTransactions.interfaces.ITransaction;
import com.nord.service.bankingTransactions.interfaces.ITransactionsFactory;
import com.nord.view.bankingTransactions.interfaces.IPayeesView;
import com.nord.view.bankingTransactions.interfaces.ITransactionsView;

import java.util.Map;

/**
 * This class holds logic to change withdraw/spending limit fetch balance and make new transaction
 * @author Jay Nimeshkumar Patel
 */
public class TransactionService extends AbstractPayee implements ITransaction {

  private ITransactionsFactory factory;
  private ITransactionsView transactionsView;
  private IPayeesView payeesView;

  public TransactionService setPayeesView(IPayeesView payeesView) {
    this.payeesView = payeesView;
    return this;
  }

  public TransactionService setFactory(ITransactionsFactory factory) {
    this.factory = factory;
    return this;
  }

  public TransactionService setTransactionsView(ITransactionsView transactionsView) {
    this.transactionsView = transactionsView;
    return this;
  }

  public void balanceEnquiry() {
    double balance = factory.makeCustomerTransactions().getCustomerBalance();
    if (balance > 0) {
      transactionsView.showCurrentBalance(balance);
    }
  }

  public void setWithdrawalLimit() {
    transactionsView.setWithdrawalLimitMenu();
    Map<String, Double> limitValues = factory.makeCustomerTransactions().getWithdrawalSpendingLimit();
    transactionsView.viewLimitsData(limitValues.get(ColumnNames.WITHDRAW), limitValues.get(ColumnNames.SPENDING));
    transactionsView.setWithdrawalLimitMenuOptions();
    int userInput = transactionsView.getIntegerInput();
    switch (userInput) {
      case 1:
        transactionsView.enterNewLimitAmount(Constants.WITHDRAW);
        changeLimit(Constants.WITHDRAW);
        break;
      case 2:
        transactionsView.enterNewLimitAmount(Constants.SPENDING);
        changeLimit(Constants.SPENDING);
        break;
      case 3:
        return;
      default:
        transactionsView.showInvalidInput();
        setWithdrawalLimit();
        break;
    }
  }

  private void changeLimit(String limitType) {
    double updatedLimit = transactionsView.getDoubleInput();
    String setLimitType;
    if (limitType.equals(Constants.WITHDRAW)) {
      setLimitType = ColumnNames.WITHDRAW;
    } else {
      setLimitType = ColumnNames.SPENDING;
    }
    if (updatedLimit <= Constants.WITH_SPEND_UPPER_LIMIT && updatedLimit > Constants.WITH_SPEND_LOWER_LIMIT) {
      factory.makeCustomerTransactions().setDbWithdrawalSpendingLimit(setLimitType, updatedLimit);
      transactionsView.limitUpdateSuccessMessage(limitType);
    } else {
      if (updatedLimit == Constants.WITH_SPEND_LOWER_LIMIT) {
        setWithdrawalLimit();
      } else {
        transactionsView.invalidLimitSelection(limitType);
        setWithdrawalLimit();
      }
    }
  }

  public void makeTransaction() {
    transactionsView.makeTransactionMenu();
    IPayeeDetailsModel selectedPayee = selectPayee(payeesView, factory.getPayeesForCustomer().getPayeeDetails());
    if (selectedPayee == null) {
      return;
    }
    transactionsView.enterAmountForMakeTransactions();
    int transferAmount = transactionsView.getIntegerInput();
    if (transferAmount == Constants.WITH_SPEND_LOWER_LIMIT) {
      return;
    }
    String comment = Constants.PAID_TO + selectedPayee.getFirstName();
    boolean result = factory.makeCustomerTransactions().makeNewTransaction(transferAmount, Constants.SUBTRACT, comment);

    if (result) {
      transactionsView.transferSuccessMessage();
    }
    balanceEnquiry();
  }
}
