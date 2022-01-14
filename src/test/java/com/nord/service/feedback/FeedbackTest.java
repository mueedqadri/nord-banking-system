package com.nord.service.feedback;

import com.nord.service.Context;
import com.nord.service.feedback.interrfaces.IEnterFeedback;
import com.nord.service.feedback.interrfaces.IFeedbackFactory;
import com.nord.service.feedback.interrfaces.IFeedbackServiceAdminSide;
import com.nord.service.feedback.interrfaces.IFeedbackServiceCustomerSide;
import com.nord.view.feedback.FeedbackAdminSideView;
import com.nord.view.feedback.FeedbackCustomerSideView;
import com.nord.view.feedback.interfaces.IFeedbackAdminSideView;
import com.nord.view.feedback.interfaces.IFeedbackCustomerSideView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * This is the test class for Feedback feature, It tests all the methods of feedback feature
 * @author Harit Patwa
 */

public class FeedbackTest {

  private final int TEST_USER_ID = 2;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  FeedbackMock feedbackMock = new FeedbackMock();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }
  @Test
  void enterFeedbackTest() {
    String input = "9";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    Context.setLoggedInUserId(TEST_USER_ID);
    IFeedbackFactory feedbackFactory = new FeedbackFactory();
    IFeedbackCustomerSideView feedbackCustomerSideView = new FeedbackCustomerSideView();
    IEnterFeedback enterFeedback = new EnterFeedback()
            .setFactory(feedbackFactory)
            .setView(feedbackCustomerSideView);
    enterFeedback.enterFeedback("Hii there");
    String actualOutPut = outputStreamCaptor.toString()
            .replace("\n", "").replace("\r", "");
    assertTrue(actualOutPut.contains(feedbackMock.MockHomePage()));

  }
  @Test
  void feedbackServiceCustomerSideTest() {
    String input = "9";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    Context.setLoggedInUserId(TEST_USER_ID);
    IFeedbackFactory feedbackFactory = new FeedbackFactory();
    IFeedbackCustomerSideView feedbackCustomerSideView = new FeedbackCustomerSideView();
    IFeedbackServiceCustomerSide feedbackServiceCustomerSide = new FeedbackServiceCustomerSide()
            .setFeedbackFactory(feedbackFactory)
            .setView(feedbackCustomerSideView);
    feedbackServiceCustomerSide.feedbackHomePage();
    String actualOutPut = outputStreamCaptor.toString()
            .replace("\n", "").replace("\r", "");
    assertTrue(actualOutPut.contains(feedbackMock.MenuPage()));

  }
  @Test
  void feedbackServiceAdminSideTest() {
    String input = "1";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    Context.setLoggedInUserId(TEST_USER_ID);
    IFeedbackFactory feedbackFactory = new FeedbackFactory();
    IFeedbackAdminSideView feedbackServiceAdminSideView = new FeedbackAdminSideView();
    IFeedbackServiceAdminSide feedbackServiceAdminSide = new FeedbackServiceAdminSide()
            .setFactory(feedbackFactory)
            .setView(feedbackServiceAdminSideView);
    feedbackFactory.getFeedbackModel().setFeedbackStatus();
    feedbackServiceAdminSide.showFeedbacks();
    String actualOutPut = outputStreamCaptor.toString()
            .replace("\n", "").replace("\r", "");
    assertTrue(actualOutPut.contains(feedbackMock.viewFeedbackPage()));

  }
}
