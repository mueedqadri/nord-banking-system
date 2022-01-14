package com.nord.service.bankingTransactions;

import com.nord.service.Context;
import com.nord.service.bankingTransactions.interfaces.ITransactionRoot;
import com.nord.service.bankingTransactions.interfaces.ITransactionsFactory;
import com.nord.service.profileManagement.ProfileManagementFactory;
import com.nord.service.profileManagement.interfaces.IProfileManagementFactory;
import com.nord.view.bankingTransactions.PayeesView;
import com.nord.view.bankingTransactions.TransactionsView;
import com.nord.view.bankingTransactions.interfaces.IPayeesView;
import com.nord.view.bankingTransactions.interfaces.ITransactionsView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for Transactions service class
 */
public class TransactionTest {

  private final int TEST_USER_ID = 1;
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void balanceEnquiryTest() {
    Context.setLoggedInUserId(TEST_USER_ID);

    String input = "3 5 11";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    TransactionsMock mock = new TransactionsMock();
    ITransactionsFactory transactionsFactory = new TransactionsFactory();
    IPayeesView payeesView = new PayeesView();
    ITransactionsView transactionsView = new TransactionsView();
    ITransactionRoot transactionService = new TransactionRoot().setFactory(transactionsFactory).setPayeesView(payeesView).setTransactionsView(transactionsView);
    transactionService.menuOptions();

    String actualOutPut = outputStreamCaptor.toString().replace("\n", "").replace("\r", "");

    assertTrue(actualOutPut.contains(mock.getCustomerBalanceMock()));
  }

  @Test
  void setWithdrawalLimit() {
    Context.setLoggedInUserId(TEST_USER_ID);

    String input = "2 5";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    TransactionsMock mock = new TransactionsMock();
    ITransactionsFactory transactionsFactory = new TransactionsFactory();
    IPayeesView payeesView = new PayeesView();
    ITransactionsView transactionsView = new TransactionsView();

    String input2 = "1 100.00";
    InputStream in2 = new ByteArrayInputStream(input2.getBytes());

    System.setIn(in2);
    ITransactionRoot transactionService = new TransactionRoot().setFactory(transactionsFactory).setPayeesView(payeesView).setTransactionsView(transactionsView);
    transactionService.menuOptions();

    String actualOutPut = outputStreamCaptor.toString().replace("\n", "").replace("\r", "");

    assertTrue(actualOutPut.contains(mock.getWithdrawalSpendingLimitMock()));

  }

  @Test
  void setZeroWithdrawalLimit() {
    Context.setLoggedInUserId(TEST_USER_ID);

    String input = "2 5";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    TransactionsMock mock = new TransactionsMock();
    ITransactionsFactory transactionsFactory = new TransactionsFactory();
    IPayeesView payeesView = new PayeesView();
    ITransactionsView transactionsView = new TransactionsView();

    String input2 = "1 0.0 3";
    InputStream in2 = new ByteArrayInputStream(input2.getBytes());

    System.setIn(in2);
    ITransactionRoot transactionService = new TransactionRoot().setFactory(transactionsFactory).setPayeesView(payeesView).setTransactionsView(transactionsView);
    transactionService.menuOptions();

    String actualOutPut = outputStreamCaptor.toString().replace("\n", "").replace("\r", "");

    assertTrue(actualOutPut.contains(mock.getWithdrawalSpendingLimitMock()));

  }

  @Test
  void setSpendingLimit() {
    Context.setLoggedInUserId(TEST_USER_ID);

    String input = "2 5";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    TransactionsMock mock = new TransactionsMock();
    ITransactionsFactory transactionsFactory = new TransactionsFactory();
    IPayeesView payeesView = new PayeesView();
    ITransactionsView transactionsView = new TransactionsView();

    String input2 = "1 100.00";
    InputStream in2 = new ByteArrayInputStream(input2.getBytes());

    System.setIn(in2);
    ITransactionRoot transactionService = new TransactionRoot().setFactory(transactionsFactory).setPayeesView(payeesView).setTransactionsView(transactionsView);
    transactionService.menuOptions();

    String actualOutPut = outputStreamCaptor.toString().replace("\n", "").replace("\r", "");

    assertTrue(actualOutPut.contains(mock.getWithdrawalSpendingLimitMock()));

  }

  @Test
  void setZeroSpendingLimit() {
    Context.setLoggedInUserId(TEST_USER_ID);

    String input = "2 5";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    TransactionsMock mock = new TransactionsMock();
    ITransactionsFactory transactionsFactory = new TransactionsFactory();
    IPayeesView payeesView = new PayeesView();
    ITransactionsView transactionsView = new TransactionsView();

    String input2 = "2 0.0 3";
    InputStream in2 = new ByteArrayInputStream(input2.getBytes());

    System.setIn(in2);
    ITransactionRoot transactionService = new TransactionRoot().setFactory(transactionsFactory).setPayeesView(payeesView).setTransactionsView(transactionsView);
    transactionService.menuOptions();

    String actualOutPut = outputStreamCaptor.toString().replace("\n", "").replace("\r", "");

    assertTrue(actualOutPut.contains(mock.getWithdrawalSpendingLimitMock()));

  }

  @Test
  void setLimitDefault() {
    Context.setLoggedInUserId(TEST_USER_ID);

    String input = "2 5";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    TransactionsMock mock = new TransactionsMock();
    ITransactionsFactory transactionsFactory = new TransactionsFactory();
    IPayeesView payeesView = new PayeesView();
    ITransactionsView transactionsView = new TransactionsView();

    String input2 = "4 3";
    InputStream in2 = new ByteArrayInputStream(input2.getBytes());

    System.setIn(in2);
    ITransactionRoot transactionService = new TransactionRoot().setFactory(transactionsFactory).setPayeesView(payeesView).setTransactionsView(transactionsView);
    transactionService.menuOptions();

    String actualOutPut = outputStreamCaptor.toString().replace("\n", "").replace("\r", "");

    assertTrue(actualOutPut.contains(mock.getWithdrawalSpendingLimitMock()));

  }

  @Test
  void makeTransactionTest() {
    Context.setLoggedInUserId(TEST_USER_ID);

    String input = "4 5";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    TransactionsMock mock = new TransactionsMock();
    ITransactionsFactory transactionsFactory = new TransactionsFactory();
    IPayeesView payeesView = new PayeesView();
    ITransactionsView transactionsView = new TransactionsView();

    String input2 = "0";
    InputStream in2 = new ByteArrayInputStream(input2.getBytes());

    System.setIn(in2);
    ITransactionRoot transactionService = new TransactionRoot().setFactory(transactionsFactory).setPayeesView(payeesView).setTransactionsView(transactionsView);
    transactionService.menuOptions();

    String actualOutPut = outputStreamCaptor.toString().replace("\n", "").replace("\r", "");

    assertTrue(actualOutPut.contains(mock.makeTransactionMock()));

  }

  @AfterEach
  void tearDown() {
    IProfileManagementFactory profileFactory = new ProfileManagementFactory();
    profileFactory.profileManagementAction().closeDbConnection();
    System.setOut(standardOut);
  }
}
