package com.nord.view.transactionHistory.interfaces;

import com.nord.view.interfaces.IUserInterface;

import java.util.Date;

public interface ITransactionHistoryView extends IUserInterface {

  void showHeader();

  void showMenu();

  void showInvalidInput();

  void transactionsListMenu();

  void lastFiveTransactionsHeader();

  void datePeriodTransactionsMenu();

  void transactionData(String payeeName, Date transactionDate, String transactionType, Double amount);

  void showEmptyTransactionsAlert();

  void showEmptyDatePeriodTransactionsAlert();

  void showErrorMessage();

  void getUserInput(String userString);

  void showFileCreatedMessage();
}
