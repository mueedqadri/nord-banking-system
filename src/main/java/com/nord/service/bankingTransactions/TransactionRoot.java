package com.nord.service.bankingTransactions;

import com.nord.service.Context;
import com.nord.service.bankingTransactions.interfaces.ITransactionRoot;
import com.nord.service.bankingTransactions.interfaces.ITransactionsFactory;
import com.nord.view.bankingTransactions.interfaces.IPayeesView;
import com.nord.view.bankingTransactions.interfaces.ITransactionsView;

/**
 * Main service class for transactions which provides menu to navigate to payee and transactions service classes
 * @author Rahul Reddy Puchakayala, Jay Nimeshkumar Patel
 */
public class TransactionRoot implements ITransactionRoot {

  private ITransactionsFactory factory;
  private IPayeesView payeesView;
  private ITransactionsView transactionsView;

  public TransactionRoot setFactory(ITransactionsFactory factory) {
    this.factory = factory;
    return this;
  }

  public TransactionRoot setPayeesView(IPayeesView payeesView) {
    this.payeesView = payeesView;
    return this;
  }

  public TransactionRoot setTransactionsView(ITransactionsView transactionsView) {
    this.transactionsView = transactionsView;
    return this;
  }

  @Override
  public void menuOptions() {
    int userInput;
    transactionsView.showHeader();
    do {
      transactionsView.showMenu();
      userInput = transactionsView.getIntegerInput();
      switch (userInput) {
        case 1:
          payeeOperations();
          break;
        case 2:
          factory.createTransactionsObject().setWithdrawalLimit();
          break;
        case 3:
          factory.createTransactionsObject().balanceEnquiry();
          break;
        case 4:
          factory.createTransactionsObject().makeTransaction();
          break;
        case 5:
          return;
        default:
          Context.setLoggedInUserId(0);
          transactionsView.showInvalidInput();
      }
    } while (true);
  }

  private void payeeOperations() {
    int userInput;
    do {
      payeesView.payeeMenuOptions();
      userInput = payeesView.getIntegerInput();
      switch (userInput) {
        case 1:
          factory.createPayeeObject().addPayee();
          break;
        case 2:
          factory.createPayeeObject().manageCustomerPayees();
          break;
        case 3:
          return;
        default:
          transactionsView.showInvalidInput();
          payeeOperations();
          break;
      }
    } while (userInput <4);
  }
}
