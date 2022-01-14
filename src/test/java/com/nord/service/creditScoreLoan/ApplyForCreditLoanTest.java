package com.nord.service.creditScoreLoan;

import com.nord.persistence.DbConnection;
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

import static org.junit.Assert.assertTrue;

/**
 * This class is testing Apply For Credit Loan feature of Credit Score Loan
 * @author Harit Patwa
 */

class ApplyForCreditLoanTest {
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private int TEST_USER_ID = 13;
  private CreditScoreLoanMock creditScoreLoanMock = new CreditScoreLoanMock();
  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void applyForAll() {
    Context.setLoggedInUserId(TEST_USER_ID);
    String input = "1";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ICreditScoreLoanFactory factory = new CreditScoreLoanFactory();
    ICreditScoreLoanView view = new CreditScoreLoanView();
    factory.applyForCreditLoan()
            .setFactory(factory)
            .setView(view)
            .applyForAll();
    String actualOutPut = outputStreamCaptor.toString()
            .replace("\n", "").replace("\r", "");
    assertTrue(actualOutPut.contains(creditScoreLoanMock.MockApplyForAll()));
  }
  @Test
  void applyForDecent() {
    TEST_USER_ID=2;
    Context.setLoggedInUserId(TEST_USER_ID);
    ICreditScoreLoanFactory factory = new CreditScoreLoanFactory();
    ICreditScoreLoanView view = new CreditScoreLoanView();
    factory.applyForCreditLoan()
            .setFactory(factory)
            .setView(view)
            .applyForDecent();
    String actualOutPut = outputStreamCaptor.toString()
            .replace("\n", "").replace("\r", "");
    assertTrue(actualOutPut.contains(creditScoreLoanMock.MockApplyDecent()));
  }
  @Test
  void applyForExceptional() {
    TEST_USER_ID=2;
    Context.setLoggedInUserId(TEST_USER_ID);
    ICreditScoreLoanFactory factory = new CreditScoreLoanFactory();
    ICreditScoreLoanView view = new CreditScoreLoanView();
    factory.applyForCreditLoan()
            .setView(view)
            .setFactory(factory)
            .applyForExceptional();
    String actualOutPut = outputStreamCaptor.toString()
            .replace("\n", "").replace("\r", "");
    assertTrue(actualOutPut.contains(creditScoreLoanMock.MockApplyExceptional()));
  }
  @AfterEach
  public void tearDown() {
    DbConnection.closeDbConnection();
  }
}