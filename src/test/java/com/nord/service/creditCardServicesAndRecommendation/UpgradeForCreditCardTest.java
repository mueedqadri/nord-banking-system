package com.nord.service.creditCardServicesAndRecommendation;

import com.nord.persistence.DbConnection;
import com.nord.service.Context;
import com.nord.service.creditCardServicesAndRecommendation.Mock.UpgradeCreditCardMock;
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
 * This class is tesing Upgrade credit card feature of credit card services and recommendation feature
 * @au
 */


class UpgradeForCreditCardTest {
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private int TEST_USER_ID = 13;
  private UpgradeCreditCardMock upgradeCreditCardMock = new UpgradeCreditCardMock();
  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }
  @Test
  void assignCreditCardToUser() {
    Context.setLoggedInUserId(TEST_USER_ID);
    String input = "1 1 9";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ICreditCardRecommendationFactory creditCardFactory = new CreditCardRecommendationFactory();
    ICreditCardServicesAndRecommendationView creditServiceView = new CreditCardServicesAndRecommendationView();
    creditCardFactory.upgradeCreditCard()
            .setFactory(creditCardFactory)
            .setView(creditServiceView)
            .assignCreditCardToUser();
    String actualOutPut = outputStreamCaptor.toString().replace("\r", "").replace("\n", "");
    String expectedOutput =  upgradeCreditCardMock.upgradeCreditCard();
    assertTrue(actualOutPut.contains(expectedOutput));

  }
  @AfterEach
  public void tearDown() {
    DbConnection.closeDbConnection();
  }
}