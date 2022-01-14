package com.nord.service.creditCardServicesAndRecommendation;

import com.nord.persistence.DbConnection;
import com.nord.service.Context;
import com.nord.service.creditCardServicesAndRecommendation.Mock.CreditCardRecommendationHomePageMock;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.ICreditCardRecommendationFactory;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.ICreditCardRecommendationService;
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

/**
 * This class is testing home page of credit card recommendation feature
 * @author Harit Patwa
 */

import static org.junit.Assert.assertTrue;

class CreditCardRecommendationServiceTest {
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private int TEST_USER_ID = 13;
  private CreditCardRecommendationHomePageMock creditCardRecommendationHomePageMock =
          new CreditCardRecommendationHomePageMock();
  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }
  @Test
  void creditCardRecommendationHomepage() {
    Context.setLoggedInUserId(TEST_USER_ID);
    String input = "9";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ICreditCardRecommendationFactory creditCardFactory = new CreditCardRecommendationFactory();
    ICreditCardServicesAndRecommendationView creditServiceView = new CreditCardServicesAndRecommendationView();
    ICreditCardTypesView typesView = new CreditCardTypesView();
    ICreditCardRecommendationService creditCardRecommendationService = new CreditCardRecommendationService()
            .setFactory(creditCardFactory)
            .setView(creditServiceView)
            .setTypeView(typesView);
    creditCardRecommendationService.creditCardRecommendationHomepage();
    String actualOutPut = outputStreamCaptor.toString().replace("\r", "").replace("\n", "");
    String expectedOutput =  creditCardRecommendationHomePageMock.showHomepageMock();
    assertTrue(actualOutPut.contains(expectedOutput));

  }
  @AfterEach
  public void tearDown() {
    DbConnection.closeDbConnection();
  }
}