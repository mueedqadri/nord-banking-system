package com.nord.service.userManagement;

import com.nord.persistence.DbConnection;
import com.nord.service.userManagement.interfaces.IUserFactory;
import com.nord.service.userManagement.interfaces.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Test all the menus for customer
 * @author Abdul Mueed Qadri
 */

public class CustomerServiceTest {
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  public void testBankingTransaction(){
    String menuName = "Banking Transaction";
    String actualOutPut = traverseMenu(1, 5);
    Assertions.assertTrue(actualOutPut.contains(menuName));
  }

  @Test
  public void testFixedDeposits(){
    String menuName = "Fixed Deposit";
    String actualOutPut = traverseMenu(2, 6);
    Assertions.assertTrue(actualOutPut.contains(menuName));
  }

  @Test
  public void testLoanMenu(){
    String menuName = "Loan Page";
    String actualOutPut = traverseMenu(3, 3);
    Assertions.assertTrue(actualOutPut.contains(menuName));
  }


  @Test
  public void testCrsMenu(){
    String menuName = "Credit Score Loan Menu";
    String actualOutPut = traverseMenu(4, 5);
    Assertions.assertTrue(actualOutPut.contains(menuName));
  }

  @Test
  public void testTransactionHistoryMenu(){
    String menuName = "Transaction History";
    String actualOutPut = traverseMenu(6, 4);
    Assertions.assertTrue(actualOutPut.contains(menuName));
  }

  @Test
  public void testBillingMenu(){
    String menuName = "Billing Page";
    String actualOutPut = traverseMenu(7, 3);
    Assertions.assertTrue(actualOutPut.contains(menuName));
  }

  @Test
  public void testCreditCardMenu(){
    String menuName = "Credit card section";
    String actualOutPut = traverseMenu(8, 9);
    Assertions.assertTrue(actualOutPut.contains(menuName));
  }

  @Test
  public void testRewardPointsMenu(){
    String menuName = "Reward Points";
    String actualOutPut = traverseMenu(9, 3);
    Assertions.assertTrue(actualOutPut.contains(menuName));
  }

  @Test
  public void testFeedbackMenu(){
    String menuName = "Feedback Menu";
    String actualOutPut = traverseMenu(10, 9);
    Assertions.assertTrue(actualOutPut.contains(menuName));
  }

  private String traverseMenu(int input1, int input2 ){
    String input = input1+" 11 ";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    IUserFactory factory = new UserFactory();
    IUserService user =  factory.createCustomer();
    String inputNext = Integer.toString(input2);
    InputStream in2 = new ByteArrayInputStream(inputNext.getBytes());
    System.setIn(in2);
    user.homePage();
    return outputStreamCaptor.toString().replace("\n", "").replace("\r", "");
  }

  @AfterEach
  public void tearDown() {
    DbConnection.closeDbConnection();
  }

}
