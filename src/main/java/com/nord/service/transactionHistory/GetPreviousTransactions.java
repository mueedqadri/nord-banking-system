package com.nord.service.transactionHistory;

import com.nord.common.Constants;
import com.nord.persistence.bankingTransactions.interfaces.ICustomerTransactionsModel;
import com.nord.service.transactionHistory.interfaces.IGetPreviousTransactions;
import com.nord.service.transactionHistory.interfaces.ITransactionHistoryFactory;
import com.nord.view.transactionHistory.interfaces.ITransactionHistoryView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Service class which performs the transaction history operations
 * @author Rahul Reddy Puchakayala
 */
public class GetPreviousTransactions implements IGetPreviousTransactions {

  private ITransactionHistoryView historyView;
  ITransactionHistoryFactory factory;
  private List<ICustomerTransactionsModel> transactionsList;

  public IGetPreviousTransactions setFactory(ITransactionHistoryFactory transactionHistoryFactory) {
    this.factory = transactionHistoryFactory;
    return this;
  }

  public IGetPreviousTransactions setView(ITransactionHistoryView historyView) {
    this.historyView = historyView;
    return this;
  }

  public List<ICustomerTransactionsModel> getTransactionsList() {
    return transactionsList;
  }

  @Override
  public IGetPreviousTransactions getAllFiveTransactions() {
    this.transactionsList = factory.getDbTransactions().getLastFiveTransactions();
    if (transactionsList.isEmpty()) {
      historyView.showEmptyTransactionsAlert();
      return this;
    }

    historyView.lastFiveTransactionsHeader();
    for (int i = 0; i < transactionsList.size(); i++) {
      historyView.transactionData(transactionsList.get(i).getComment(), transactionsList.get(i).getTransactionDate(), transactionsList.get(i).getTransactionType(), transactionsList.get(i).getAmount());
    }
    return this;
  }

  @Override
  public IGetPreviousTransactions getAllDatePeriodTransactions() {
    LocalDate fromDate, toDate;
    boolean dateCompare;
    historyView.datePeriodTransactionsMenu();

    do {
      try {
        historyView.getUserInput(Constants.FROM_DATE);
        fromDate = historyView.getDateInputLessThanToday();
        historyView.getUserInput(Constants.TO_DATE);
        toDate = historyView.getDateInputLessThanToday();
        dateCompare = fromDate.isAfter(toDate);
        if (dateCompare) {
          historyView.showInvalidInput();
        }
      } catch (DateTimeParseException exception) {
        historyView.showErrorMessage();
        return null;
      }
    } while (dateCompare);

    Date fromDateDb = Date.valueOf(fromDate);
    Date toDateDb = Date.valueOf(toDate);

    historyView.transactionsListMenu();

    this.transactionsList = factory.getDbTransactions().getDatePeriodTransactions(fromDateDb, toDateDb);

    if (transactionsList.isEmpty()) {
      historyView.showEmptyDatePeriodTransactionsAlert();
      return this;
    }

    for (ICustomerTransactionsModel iCustomerTransactionsModel : transactionsList) {
      historyView.transactionData(iCustomerTransactionsModel.getComment(), iCustomerTransactionsModel.getTransactionDate(), iCustomerTransactionsModel.getTransactionType(), iCustomerTransactionsModel.getAmount());
    }
    return this;
  }

  @Override
  public IGetPreviousTransactions printTransactionsToFile() {
    try {
      String filePath = Constants.STATEMENT_FILE_PATH;
      File statementFile = new File(filePath);
      if (statementFile.exists() && statementFile.isFile()) {
        statementFile.delete();
      }
      statementFile.createNewFile();
      PrintStream stdout = System.out;
      PrintStream ps = new PrintStream(new FileOutputStream(filePath, true));
      System.setOut(ps);
      historyView.transactionsListMenu();
      this.transactionsList = factory.getDbTransactions().getLastFiveTransactions();
      for (int i = 0; i < transactionsList.size(); i++) {
        historyView.transactionData(transactionsList.get(i).getComment(), transactionsList.get(i).getTransactionDate(), transactionsList.get(i).getTransactionType(), transactionsList.get(i).getAmount());
      }
      System.setOut(stdout);
      ps.close();
      historyView.showFileCreatedMessage();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this;
  }
}
