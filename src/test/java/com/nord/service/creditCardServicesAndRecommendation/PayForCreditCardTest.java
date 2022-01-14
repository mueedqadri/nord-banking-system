package com.nord.service.creditCardServicesAndRecommendation;

import com.nord.persistence.DbConnection;
import com.nord.service.Context;
import com.nord.service.creditCardServicesAndRecommendation.Mock.PayCreditCardBillMock;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.ICreditCardRecommendationFactory;
import com.nord.view.creditCardServicesAndRecommendation.CreditCardServicesAndRecommendationView;
import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardServicesAndRecommendationView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * This class is testing paying feature of credit card bill
 * @author Harit Patwa
 */

class PayForCreditCardTest {
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private int TEST_USER_ID = 13;
  private PayCreditCardBillMock payCreditCardBillMock = new PayCreditCardBillMock();
  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void payCreditCardBill() {
    Context.setLoggedInUserId(TEST_USER_ID);
    String input = "9";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ICreditCardRecommendationFactory creditCardFactory = new CreditCardRecommendationFactory();
    ICreditCardServicesAndRecommendationView creditServiceView = new CreditCardServicesAndRecommendationView();
    creditCardFactory.payCreditCardBill()
            .setView(creditServiceView)
            .setFactory(creditCardFactory)
            .payCreditCardBill();
    String actualOutPut = outputStreamCaptor.toString().replace("\r", "").replace("\n", "");
    String expectedOutput =  payCreditCardBillMock.payBillMock();
    assertTrue(actualOutPut.contains(expectedOutput));
  }
  @AfterEach
  public void tearDown() {
    DbConnection.closeDbConnection();
  }
}