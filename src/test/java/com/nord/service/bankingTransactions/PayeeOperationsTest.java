package com.nord.service.bankingTransactions;

import com.nord.service.Context;
import com.nord.service.bankingTransactions.interfaces.ITransactionsFactory;
import com.nord.service.profileManagement.ProfileManagementFactory;
import com.nord.service.profileManagement.interfaces.IProfileManagementFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PayeeOperations service class
 */
public class PayeeOperationsTest {

  private final int TEST_USER_ID = 1;
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void addPayeeTestTrue() {
    Context.setLoggedInUserId(TEST_USER_ID);
    CustomerPayeeMock mock = new CustomerPayeeMock();
    StringBuilder input = new StringBuilder();
    for (Object values : mock.addNewPayee()) {
      input.append(values).append(" ");
    }

    input.append("y");
    InputStream in = new ByteArrayInputStream(input.toString().getBytes());
    System.setIn(in);

    ITransactionsFactory transactionsFactory = new TransactionsFactory();
    transactionsFactory.createPayeeObject().addPayee();

    String actualOutPut = outputStreamCaptor.toString().replace("\n", "").replace("\r", "");
    assertTrue(actualOutPut.contains("Payee added successfully!"));
  }

  @Test
  void addPayeeTestFalse() {
    Context.setLoggedInUserId(TEST_USER_ID);
    CustomerPayeeMock mock = new CustomerPayeeMock();
    StringBuilder input = new StringBuilder();
    for (Object values : mock.addNewPayee()) {
      input.append(values).append(" ");
    }

    input.append("n");
    InputStream in = new ByteArrayInputStream(input.toString().getBytes());
    System.setIn(in);

    ITransactionsFactory transactionsFactory = new TransactionsFactory();
    transactionsFactory.createPayeeObject().addPayee();

    String actualOutPut = outputStreamCaptor.toString().replace("\n", "").replace("\r", "");
    assertNotNull(actualOutPut);
  }

  @Test
  void addPayeeTestInvalidInput() {
    Context.setLoggedInUserId(TEST_USER_ID);
    CustomerPayeeMock mock = new CustomerPayeeMock();
    StringBuilder input = new StringBuilder();
    for (Object values : mock.addNewPayee()) {
      input.append(values).append(" ");
    }

    input.append("cancel");
    InputStream in = new ByteArrayInputStream(input.toString().getBytes());
    System.setIn(in);

    ITransactionsFactory transactionsFactory = new TransactionsFactory();
    transactionsFactory.createPayeeObject().addPayee();

    String actualOutPut = outputStreamCaptor.toString().replace("\n", "").replace("\r", "");
    assertNotNull(actualOutPut);
  }

  @Test
  void deletePayeeTestTrue() {
    Context.setLoggedInUserId(TEST_USER_ID);
    String input = "1";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ITransactionsFactory transactionsFactory = new TransactionsFactory();
    CustomerPayeeMock mock = new CustomerPayeeMock();
    boolean result = transactionsFactory.getPayeesForCustomer().deletePayee(mock.getPayeeDetailsMock().getPayeeId());
    assertTrue(result);
  }

  @Test
  void deletePayeeTestFalse() {
    Context.setLoggedInUserId(0);

    String input = "1 1";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    ITransactionsFactory transactionsFactory = new TransactionsFactory();
    transactionsFactory.createPayeeObject().manageCustomerPayees();

    String actualOutPut = outputStreamCaptor.toString().replace("\n", "").replace("\r", "");
    assertFalse(actualOutPut.contains("Successfully removed payee!"));
  }

  @Test
  void editPayeeDetailsFirstNameTest() {
    Context.setLoggedInUserId(TEST_USER_ID);

    String input = "1 TestUser";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    CustomerPayeeMock mock = new CustomerPayeeMock();
    ITransactionsFactory transactionsFactory = new TransactionsFactory();
    boolean result = transactionsFactory.createPayeeObject().editPayeeDetails(2);

    assertEquals(mock.editPayeeDetailsMock(), result);
  }

  @Test
  void editPayeeDetailsNickNameTest() {
    Context.setLoggedInUserId(TEST_USER_ID);

    String input = "3 Avenger";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ITransactionsFactory transactionsFactory = new TransactionsFactory();
    boolean result = transactionsFactory.createPayeeObject().editPayeeDetails(1);

    assertFalse(result);
  }

  @AfterEach
  public void tearDown() {
    IProfileManagementFactory profileFactory = new ProfileManagementFactory();
    profileFactory.profileManagementAction().closeDbConnection();
    System.setOut(standardOut);
  }

}
