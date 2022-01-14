package com.nord.view.bankingTransactions.interfaces;

import com.nord.view.interfaces.IUserInterface;

public interface ITransactionsView extends IUserInterface {

  void showHeader();

  void showMenu();

  void showInvalidInput();

  void setWithdrawalLimitMenu();

  void setWithdrawalLimitMenuOptions();

  void viewLimitsData(double withdraw_limit, double spending_limit);

  void makeTransactionMenu();

  void showCurrentBalance(double balance);

  void limitUpdateSuccessMessage(String limitType);

  void enterNewLimitAmount(String limitType);

  void invalidLimitSelection(String limitType);

  void enterAmountForMakeTransactions();

  void transferSuccessMessage();
}
