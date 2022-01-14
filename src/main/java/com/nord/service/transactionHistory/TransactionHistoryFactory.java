package com.nord.service.transactionHistory;

import com.nord.persistence.transactionHistory.CustomerTransactionsHistory;
import com.nord.persistence.transactionHistory.interfaces.ICustomerTransactionsHistory;
import com.nord.service.transactionHistory.interfaces.IGetPreviousTransactions;
import com.nord.service.transactionHistory.interfaces.ITransactionHistoryFactory;
import com.nord.service.transactionHistory.interfaces.ITransactionHistoryService;
import com.nord.view.transactionHistory.TransactionHistoryView;
import com.nord.view.transactionHistory.interfaces.ITransactionHistoryView;

/**
 * This class represents the factory class for TransactionHistory feature
 * @author Jay Nimeshkumar Patel, Rahul Reddy Puchakayala
 */
public class TransactionHistoryFactory implements ITransactionHistoryFactory {

  @Override
  public ICustomerTransactionsHistory getDbTransactions() {
    return new CustomerTransactionsHistory();
  }

  private ITransactionHistoryView createTransactionHistoryView() {
    return new TransactionHistoryView();
  }

  @Override
  public IGetPreviousTransactions showLastFiveTransactions() {
    return new GetPreviousTransactions().setFactory(this).setView(createTransactionHistoryView()).getAllFiveTransactions();
  }

  @Override
  public IGetPreviousTransactions showDatePeriodTransactions() {
    return new GetPreviousTransactions().setFactory(this).setView(createTransactionHistoryView()).getAllDatePeriodTransactions();
  }

  @Override
  public IGetPreviousTransactions printTransactionStatement() {
    return new GetPreviousTransactions().setFactory(this).setView(createTransactionHistoryView()).printTransactionsToFile();
  }

}
