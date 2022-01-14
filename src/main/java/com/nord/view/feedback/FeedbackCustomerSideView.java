package com.nord.view.feedback;

import com.nord.view.AbstractUserInterface;
import com.nord.view.feedback.interfaces.IFeedbackCustomerSideView;

/**
 * This class is responsible for Feedback feature of Customer Side's presentation layer
 * @author Harit Patwa
 */

public class FeedbackCustomerSideView extends AbstractUserInterface implements IFeedbackCustomerSideView {

  @Override
  public void showHeader() {
      System.out.println("=============================================================");
      System.out.println("                      Feedback Menu           ");
      System.out.println("=============================================================");

  }
  @Override
  public void showInvalidInput() {
    System.out.println("Something went wrong, Please try again");
  }
  @Override
  public void showMenu() {
    System.out.println("1. Enter Feedback");
  }

  public void showReturnMenu() {
    System.out.println("9. To return to previous Menu");
  }
  public void enterFeedback()
  {
    System.out.println("Please Enter your feedback here!");
    System.out.println("We really value it");
  }

  public void showCongratulationsMenu() {
    System.out.println("Congratulations! Your feedback has been recorded");

  }
}
