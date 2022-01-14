package com.nord.service.transactionHistory;

import com.nord.persistence.bankingTransactions.CustomerTransactionsModel;
import com.nord.persistence.bankingTransactions.interfaces.ICustomerTransactionsModel;
import com.nord.service.Context;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Mock class for TransactionHistory persistence class
 */
public class TransactionHistoryMock {

  public List<ICustomerTransactionsModel> getLastTransactionsData() {
    String transactionDate;
    ICustomerTransactionsModel customerTransactionsModel = new CustomerTransactionsModel();
    List<ICustomerTransactionsModel> transactionsModelList = new ArrayList<>();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    customerTransactionsModel.setTransactionId(1);
    customerTransactionsModel.setUserId(Context.getLoggedInUserId());
    customerTransactionsModel.setComment("Paid to: Rahul");
    customerTransactionsModel.setAmount(100);
    customerTransactionsModel.setTransactionType("Debit");
    transactionDate = "01/07/2021";
    customerTransactionsModel.setTransactionDate(Date.valueOf(LocalDate.parse(transactionDate, dateTimeFormatter)));

    transactionsModelList.add(customerTransactionsModel);
    return transactionsModelList;
  }

  public List<ICustomerTransactionsModel> getDatePeriodTransactionsData() {
    String transactionDate;
    ICustomerTransactionsModel customerTransactionsModel = new CustomerTransactionsModel();
    List<ICustomerTransactionsModel> transactionsModelList = new ArrayList<>();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    customerTransactionsModel.setTransactionId(1);
    customerTransactionsModel.setUserId(Context.getLoggedInUserId());
    customerTransactionsModel.setComment("Paid to: Rahul");
    customerTransactionsModel.setAmount(100);
    customerTransactionsModel.setTransactionType("Debit");
    transactionDate = "01/07/2021";
    customerTransactionsModel.setTransactionDate(Date.valueOf(LocalDate.parse(transactionDate, dateTimeFormatter)));

    transactionsModelList.add(customerTransactionsModel);

    customerTransactionsModel.setTransactionId(1);
    customerTransactionsModel.setUserId(Context.getLoggedInUserId());
    customerTransactionsModel.setComment("Paid to: Vishal");
    customerTransactionsModel.setAmount(50);
    customerTransactionsModel.setTransactionType("Debit");
    transactionDate = "05/06/2021";
    customerTransactionsModel.setTransactionDate(Date.valueOf(LocalDate.parse(transactionDate, dateTimeFormatter)));
    transactionsModelList.add(customerTransactionsModel);
    return transactionsModelList;
  }
}
