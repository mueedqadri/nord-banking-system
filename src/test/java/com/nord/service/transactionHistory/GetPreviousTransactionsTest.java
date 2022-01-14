package com.nord.service.transactionHistory;

import com.nord.persistence.DbConnection;
import com.nord.service.Context;
import com.nord.service.transactionHistory.interfaces.IGetPreviousTransactions;
import com.nord.service.transactionHistory.interfaces.ITransactionHistoryFactory;
import com.nord.service.transactionHistory.interfaces.ITransactionHistoryService;
import com.nord.view.transactionHistory.TransactionHistoryView;
import com.nord.view.transactionHistory.interfaces.ITransactionHistoryView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for GetPreviousTransactions service class
 */
public class GetPreviousTransactionsTest {

  private final int TEST_USER_ID = 10;
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void historyToMainMenuTest() {
    Context.setLoggedInUserId(TEST_USER_ID);

    String input = "6 4";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ITransactionHistoryFactory historyFactory = new TransactionHistoryFactory();
    ITransactionHistoryView view = new TransactionHistoryView();
    ITransactionHistoryService th = new TransactionHistoryService().setFactory(historyFactory).setView(view);
    th.transactionMenu();

    String actualOutPut = outputStreamCaptor.toString()
        .replace("\n", "").replace("\r", "");

    assertNotNull(actualOutPut);
  }

  @Test
  void showLastFiveTransactionsTest() {
    Context.setLoggedInUserId(TEST_USER_ID);
    TransactionHistoryMock mock = new TransactionHistoryMock();
    ITransactionHistoryFactory factory = new TransactionHistoryFactory();
    IGetPreviousTransactions serviceResult = factory.showLastFiveTransactions();

    int userID = serviceResult.getTransactionsList().get(0).getUserId();
    assertEquals(mock.getLastTransactionsData().get(0).getUserId(), userID);
  }

  @Test
  void getAllFiveTransactionsShowAlert() {
    Context.setLoggedInUserId(0);
    ITransactionHistoryFactory factory = new TransactionHistoryFactory();
    factory.showLastFiveTransactions();

    String actualOutPut = outputStreamCaptor.toString()
        .replace("\n", "").replace("\r", "");

    assertTrue(actualOutPut.contains("No transactions exists!"));
  }

  @Test
  void getAllFiveTransactionsEmpty() {
    Context.setLoggedInUserId(0);
    ITransactionHistoryFactory factory = new TransactionHistoryFactory();
    IGetPreviousTransactions serviceResult =factory.showLastFiveTransactions();

    assertTrue(serviceResult.getTransactionsList().isEmpty());
  }

  @Test
  void getAllDatePeriodTransactionsTest() {
    Context.setLoggedInUserId(TEST_USER_ID);
    String userInput = "01/01/2021 20/07/2021";
    InputStream in = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(in);

    TransactionHistoryMock mock = new TransactionHistoryMock();
    ITransactionHistoryFactory factory = new TransactionHistoryFactory();
    IGetPreviousTransactions serviceResult = factory.showDatePeriodTransactions();

    int userID = serviceResult.getTransactionsList().get(0).getUserId();
    assertEquals(mock.getDatePeriodTransactionsData().get(0).getUserId(), userID);
  }

  @Test
  void getAllDatePeriodTransactionsEmpty() {
    Context.setLoggedInUserId(TEST_USER_ID);

    String userInput = "01/01/2020 01/02/2021";
    InputStream in = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(in);

    ITransactionHistoryFactory factory = new TransactionHistoryFactory();
    IGetPreviousTransactions serviceResult = factory.showDatePeriodTransactions();

    assertTrue(serviceResult.getTransactionsList().isEmpty());
  }

  @Test
  void invalidDatePeriodTransactionsTest() {
    Context.setLoggedInUserId(TEST_USER_ID);

    String userInput = "01/07/2021 05/06/2021 01/01/2021 01/02/2021";
    InputStream in = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(in);

    ITransactionHistoryFactory factory = new TransactionHistoryFactory();
    factory.showDatePeriodTransactions();

    String actualOutPut = outputStreamCaptor.toString()
        .replace("\n", "").replace("\r", "");

    assertTrue(actualOutPut.contains("Invalid selection! Please select a valid option:"));
  }

  @Test
  void printTransactionsToFile() {
    Context.setLoggedInUserId(TEST_USER_ID);

    ITransactionHistoryFactory factory = new TransactionHistoryFactory();
    factory.printTransactionStatement();

    String actualOutPut = outputStreamCaptor.toString()
            .replace("\n", "").replace("\r", "");
    assertTrue(actualOutPut.contains("Statement file created!"));
  }

  @Test
  void printTransactionsToFileEmptyList() {
    Context.setLoggedInUserId(0);

    ITransactionHistoryFactory factory = new TransactionHistoryFactory();
    IGetPreviousTransactions serviceResult =factory.printTransactionStatement();

    assertTrue(serviceResult.getTransactionsList().isEmpty());
  }

  @AfterEach
  public void tearDown() {
    DbConnection.closeDbConnection();
    System.setOut(standardOut);
  }
}