package com.nord.service.creditScoreLoan;

import com.nord.persistence.DbConnection;
import com.nord.persistence.creditScoreLoan.CreditScoreLoanModel;
import com.nord.persistence.creditScoreLoan.interfaces.ICreditScoreLoanModel;
import com.nord.service.Context;
import com.nord.service.creditScoreLoan.interfaces.ICreditScoreLoanFactory;
import com.nord.service.creditScoreLoan.mock.CreditScoreLoanMock;
import com.nord.view.creditScoreLoan.CreditScoreLoanView;
import com.nord.view.creditScoreLoan.interfaces.ICreditScoreLoanView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class is testing Pay For Credit Loan feature of Credit Score Loan
 * @author Harit Patwa
 */

class PayCreditLoanTest {

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private int TEST_USER_ID = 13;
  private CreditScoreLoanMock creditScoreLoanMock = new CreditScoreLoanMock();
  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }
  @Test
  void payCreditLoanByGivenLoans() {
    Context.setLoggedInUserId(TEST_USER_ID);
    String input = "1";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ICreditScoreLoanFactory factory = new CreditScoreLoanFactory();
    ICreditScoreLoanView creditScoreLoanView = new CreditScoreLoanView();
    factory.payForCreditLoan()
            .setFactory(factory)
            .setView(creditScoreLoanView)
            .payCreditLoanByGivenLoans(factory.getCreditScoreLoanHistoryModel().getCreditScoreLoanHistory());
    String actualOutPut = outputStreamCaptor.toString()
            .replace("\n", "").replace("\r", "");
    assertTrue(actualOutPut.contains(creditScoreLoanMock.MockPayCreditLoan()));
  }
  @Test
  void getPayLoanCount() {
    Context.setLoggedInUserId(TEST_USER_ID);
    List<ICreditScoreLoanModel> list = new ArrayList<>();
    list.add(new CreditScoreLoanModel());
    list.add(new CreditScoreLoanModel());
    ICreditScoreLoanFactory factory = new CreditScoreLoanFactory();
    ICreditScoreLoanView creditScoreLoanView = new CreditScoreLoanView();
    int actualOutput = factory.payForCreditLoan()
            .setFactory(factory)
            .setView(creditScoreLoanView)
            .getPayLoanCount(list);
    int expectedOutput= 2;
    assertEquals(expectedOutput,actualOutput);
  }
  @Test
  void payCreditLoanByDateTest()
  {
    TEST_USER_ID=12;
    Context.setLoggedInUserId(TEST_USER_ID);
    ICreditScoreLoanFactory factory = new CreditScoreLoanFactory();
    int amount =3;
    Date date = new Date();
    ICreditScoreLoanView creditScoreLoanView = new CreditScoreLoanView();
    boolean expectedOutPut = factory.payForCreditLoan()
            .setView(creditScoreLoanView)
            .setFactory(factory)
            .payCreditLoanByDate(amount,date);
    assertTrue(expectedOutPut);
  }
  @AfterEach
  public void tearDown() {
    DbConnection.closeDbConnection();
  }
}