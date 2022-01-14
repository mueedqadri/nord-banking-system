package com.nord.service.transactionHistory.interfaces;

import com.nord.persistence.bankingTransactions.interfaces.ICustomerTransactionsModel;
import com.nord.view.transactionHistory.interfaces.ITransactionHistoryView;

import java.util.List;

/**
 * Interface which is implemented by GetPreviousTransactions service class
 * @author Rahul Reddy Puchakayala
 */
public interface IGetPreviousTransactions {

  IGetPreviousTransactions setFactory(ITransactionHistoryFactory transactionHistoryFactory);

  IGetPreviousTransactions setView(ITransactionHistoryView historyView);

  IGetPreviousTransactions getAllFiveTransactions();

  IGetPreviousTransactions getAllDatePeriodTransactions();

  IGetPreviousTransactions printTransactionsToFile();

  List<ICustomerTransactionsModel> getTransactionsList();
}
