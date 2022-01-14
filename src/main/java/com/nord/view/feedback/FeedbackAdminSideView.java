package com.nord.view.feedback;

import com.nord.view.AbstractUserInterface;
import com.nord.view.feedback.interfaces.IFeedbackAdminSideView;

import java.util.List;

/**
 * This class is responsible for Feedback feature of Admin Side's presentation layer
 * @author Harit Patwa
 */
public class FeedbackAdminSideView extends AbstractUserInterface implements IFeedbackAdminSideView {

  @Override
  public void showHeader() {
    System.out.println("=============================================================");
    System.out.println("                      Feedback Menu           ");
    System.out.println("=============================================================");
  }
  @Override
  public void showMenu() {
    System.out.println("1. Enter Feedback");
  }
  @Override
  public void showReturnMenu() {
    System.out.println("9. to return back to previous menu");
  }

  @Override
  public void viewFeedbacks(List<String> feedbacks) {
    System.out.println("Feedbacks: \n");
    for(int i=0;i<feedbacks.size();i++)
    {
      System.out.println((i+1)+". "+ feedbacks.get(i));
    }
  }

  @Override
  public void showReturnMenuWithNumber(int number) {
    System.out.println(number+". to return back to previous menu");
  }

  @Override
  public void showInvalidInput() {
    System.out.println("Something went wrong, Please try again");
  }
  @Override
  public void noNewFeedbacks()
  {
    System.out.println("No new feedbacks, you must have read every feedback");
  }
}
