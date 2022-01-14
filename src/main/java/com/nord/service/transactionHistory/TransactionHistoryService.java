package com.nord.service.transactionHistory;

import com.nord.service.transactionHistory.interfaces.ITransactionHistoryFactory;
import com.nord.service.transactionHistory.interfaces.ITransactionHistoryService;
import com.nord.view.transactionHistory.interfaces.ITransactionHistoryView;

/**
 * Service class for transactionHistory feature which provides the main menu for this feature
 * @author Jay Nimeshkumar Patel, Rahul Reddy Puchakayala
 */
public class TransactionHistoryService implements ITransactionHistoryService {

  private ITransactionHistoryView historyView;
  private ITransactionHistoryFactory factory;

  public TransactionHistoryService() {
  }

  public TransactionHistoryService setFactory(ITransactionHistoryFactory factory) {
    this.factory = factory;
    return this;
  }

  public TransactionHistoryService setView(ITransactionHistoryView historyView) {
    this.historyView = historyView;
    return this;
  }

  @Override
  public void transactionMenu() {
    int userInput;
    historyView.showHeader();
    do {
      historyView.showMenu();
      userInput = historyView.getIntegerInput();
      switch (userInput) {
        case 1:
          factory.showLastFiveTransactions();
          break;
        case 2:
          factory.showDatePeriodTransactions();
          break;
        case 3:
          factory.printTransactionStatement();
          break;
        case 4:
          factory.getDbTransactions().closeDbConnection();
          return;
        default:
          historyView.showInvalidInput();
      }
    } while (true);
  }

}
