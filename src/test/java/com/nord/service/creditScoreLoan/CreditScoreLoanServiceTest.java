package com.nord.service.creditScoreLoan;

import com.nord.persistence.DbConnection;
import com.nord.service.Context;
import com.nord.service.creditScoreLoan.interfaces.ICreditScoreLoanFactory;
import com.nord.service.creditScoreLoan.interfaces.ICreditScoreLoanService;
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
 * This class is testing Credit Score Loan Service class's methods of Credit Score Loan feature.
 * @author Harit Patwa
 */

class CreditScoreLoanServiceTest {
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private int TEST_USER_ID = 13;
  private CreditScoreLoanMock creditScoreLoanMock = new CreditScoreLoanMock();
  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }
  @Test
  void creditScoreLoanHomePage() {
    Context.setLoggedInUserId(TEST_USER_ID);
    String input = "5";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ICreditScoreLoanFactory factory = new CreditScoreLoanFactory();
    ICreditScoreLoanView creditScoreLoanViewview = new CreditScoreLoanView();
    ICreditScoreLoanService creditScoreLoanService = new CreditScoreLoanService()
            .setFactory(factory).setView(creditScoreLoanViewview);
    creditScoreLoanService.creditScoreLoanHomePage();
    String actualOutPut = outputStreamCaptor.toString()
            .replace("\n", "").replace("\r", "");
    assertTrue(actualOutPut.contains(creditScoreLoanMock.MockHomePage()));
  }
  @Test
  void updateCreditLoanService() {
    ICreditScoreLoanFactory factory = new CreditScoreLoanFactory();
    ICreditScoreLoanService creditScoreLoanService = new CreditScoreLoanService()
            .setFactory(factory);
    assertTrue(creditScoreLoanService.updateCreditLoanService());
  }
  @Test
  void showCreditScoreLoanService() {
    Context.setLoggedInUserId(TEST_USER_ID);
    String input = "1";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ICreditScoreLoanFactory factory = new CreditScoreLoanFactory();
    ICreditScoreLoanView creditScoreLoanViewview = new CreditScoreLoanView();
    ICreditScoreLoanService creditScoreLoanService = new CreditScoreLoanService(factory)
            .setFactory(factory).setView(creditScoreLoanViewview);
    creditScoreLoanService.showCreditScoreLoanService();
    String actualOutPut = outputStreamCaptor.toString()
            .replace("\n", "").replace("\r", "");

    assertTrue(actualOutPut.contains(creditScoreLoanMock.MockTipsPage()));
  }
  @AfterEach
  public void tearDown() {
    DbConnection.closeDbConnection();
  }
}