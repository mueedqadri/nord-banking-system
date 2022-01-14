package com.nord.view.transactionHistory;

import com.nord.common.Constants;
import com.nord.view.AbstractUserInterface;
import com.nord.view.transactionHistory.interfaces.ITransactionHistoryView;

import java.util.Date;

/**
 * This class is responsible for the presentation layer of Transaction History Feature
 * @author Jay Nimeshkumar Patel, Rahul Reddy Puchakayala
 */
public class TransactionHistoryView extends AbstractUserInterface implements ITransactionHistoryView {

  @Override
  public void showHeader() {
    System.out.println("=============================================================");
    System.out.println("                     Transaction History            ");
    System.out.println("=============================================================");
  }

  @Override
  public void showMenu() {
    System.out.println("\n1. View last 5 transactions");
    System.out.println("2. View transactions made in date period");
    System.out.println("3. Create a statement file");
    System.out.println("4. Exit");

    System.out.println("Enter your selection: ");
  }

  @Override
  public void showInvalidInput() {
    System.out.println("Invalid selection! Please select a valid option:");
  }

  @Override
  public void transactionsListMenu() {
    System.out.printf(Constants.FORMATTING_TRANS_HISTORY, "Comment   ","Date(yyyy-mm-dd)","Type","Amount  ");
    System.out.println();
    System.out.println("------------------------------------------------------------------" +
        "-------------------------------");
  }

  @Override
  public void lastFiveTransactionsHeader() {
    System.out.println("=============================================================");
    System.out.println("                     Last 5 Transactions            ");
    System.out.println("=============================================================\n");

    transactionsListMenu();
  }

  @Override
  public void datePeriodTransactionsMenu() {
    System.out.println("=============================================================");
    System.out.println("              Transactions within a Date Period            ");
    System.out.println("=============================================================\n");

    System.out.println("Enter date values (dd/MM/yyyy):\n");
  }

  @Override
  public void transactionData(String comment, Date transactionDate, String transactionType, Double amount) {
    System.out.printf(Constants.FORMATTING_TRANS_HISTORY,comment, transactionDate+"   ", transactionType, amount+"  ");
    System.out.println();
  }

  @Override
  public void showEmptyTransactionsAlert() {
    System.out.println("No transactions exists!");
  }

  @Override
  public void showEmptyDatePeriodTransactionsAlert() {
    System.out.println("No transactions exists in the given date range!");
  }

  @Override
  public void showErrorMessage() {
    System.out.println("Something went wrong!");
  }

  @Override
  public void getUserInput(String userString) {
    System.out.println("Enter " + userString + " value:");
  }

  @Override
  public void showFileCreatedMessage() {
    System.out.println("Statement file created!");
  }
}
