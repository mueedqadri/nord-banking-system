package com.nord.service.creditCardServicesAndRecommendation;

import com.nord.persistence.DbConnection;
import com.nord.service.Context;
import com.nord.service.creditCardServicesAndRecommendation.Mock.RecommendCreditCardMock;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.ICreditCardRecommendationFactory;
import com.nord.view.creditCardServicesAndRecommendation.CreditCardServicesAndRecommendationView;
import com.nord.view.creditCardServicesAndRecommendation.CreditCardTypesView;
import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardServicesAndRecommendationView;
import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardTypesView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * This class is tesing Recommendation feature of credit card recommendation feature
 * @author Harit Patwa
 */

class RecommendForCreditCardTest {
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private int TEST_USER_ID = 13;
  private RecommendCreditCardMock recommendCreditCardMock = new RecommendCreditCardMock();
  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }
  
  @Test
  void recommendCreditCard() {
    Context.setLoggedInUserId(TEST_USER_ID);
    String input = "22 2000 1500 9";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ICreditCardRecommendationFactory creditCardFactory = new CreditCardRecommendationFactory();
    ICreditCardTypesView typesView = new CreditCardTypesView();
    ICreditCardServicesAndRecommendationView creditServiceView = new CreditCardServicesAndRecommendationView();
    creditCardFactory.recommendCreditCard()
            .setView(creditServiceView)
            .setCreditCardTypeView(typesView)
            .recommendCreditCard();
    String actualOutPut = outputStreamCaptor.toString().replace("\r", "").replace("\n", "");
    String expectedOutput =  recommendCreditCardMock.recommendCreditCard();
    assertTrue(actualOutPut.contains(expectedOutput));
  }
  @AfterEach
  public void tearDown() {
    DbConnection.closeDbConnection();
  }
}