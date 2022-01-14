package com.nord.view.bankingTransactions;

import com.nord.view.AbstractUserInterface;
import com.nord.view.bankingTransactions.interfaces.ITransactionsView;

/**
 * This class is responsible for the presentation layer of banking transaction feature
 * @author Jay Nimeshkumar Patel, Rahul Reddy Puchakayala
 */
public class TransactionsView extends AbstractUserInterface implements ITransactionsView {

  @Override
  public void showHeader() {
    System.out.println("=============================================================");
    System.out.println("                    Banking Transaction            ");
    System.out.println("=============================================================\n");
  }

  @Override
  public void showMenu() {
    System.out.println("1. Add or Manage Payee");
    System.out.println("2. Set Withdrawal/Spending Limit");
    System.out.println("3. Perform Balance Enquiry");
    System.out.println("4. Transfer money to another account");
    System.out.println("5. Exit");
    System.out.print("Enter your selection: \n");
  }

  @Override
  public void setWithdrawalLimitMenu() {
    System.out.println("=============================================================");
    System.out.println("                 Set Withdrawal/Spending Limit           ");
    System.out.println("=============================================================\n");
  }

  @Override
  public void setWithdrawalLimitMenuOptions() {
    System.out.println("\n1. Change Withdrawal Limit\n2. Change Spending Limit \n3. Back to Previous Menu" + "\nNOTE: Withdraw/Spending Limit can't exceed 10,000 CAD");
    System.out.println("Enter your choice: ");
  }

  @Override
  public void viewLimitsData(double withdraw_limit, double spending_limit) {
    System.out.println("Current Withdrawal Limit: " + withdraw_limit + "\nCurrent Spending Limit: " + spending_limit);
  }

  @Override
  public void limitUpdateSuccessMessage(String limitType) {
    System.out.println(limitType + " Limit Successfully Updated.\n");
  }

  @Override
  public void enterNewLimitAmount(String limitType) {
    System.out.println("Enter the amount to " + limitType + " and 0 to exit: ");
  }

  @Override
  public void invalidLimitSelection(String limitType) {
    System.out.println("This " + limitType + " limit is unacceptable, try setting it below 10,000 CAD");
  }

  @Override
  public void makeTransactionMenu() {
    System.out.println("=============================================================");
    System.out.println("                       Make Transaction           ");
    System.out.println("=============================================================\n");
  }

  @Override
  public void enterAmountForMakeTransactions() {
    System.out.println("Enter the amount to send and 0 to exit: ");
  }

  @Override
  public void transferSuccessMessage() {
    System.out.println("Transaction Completed!");
  }

  @Override
  public void showInvalidInput() {
    System.out.println("Please enter a valid input!\n");
  }

  @Override
  public void showCurrentBalance(double balance) {
    System.out.println("\nYour current balance is: " + balance + "\n");
  }

}
