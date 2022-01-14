package com.nord.service.feedback;

/**
 * This class represents Feedback feature mocks
 * @author Harit Patwa
 */
public class FeedbackMock {

  public String MockHomePage() {
    StringBuilder sb=new StringBuilder();
    sb.append("Congratulations! Your feedback has been recorded");
    sb.append("9. To return to previous Menu");
    return sb.toString();
  }
  public String MenuPage() {
    StringBuilder sb=new StringBuilder();
    sb.append("=============================================================");
    sb.append("                      Feedback Menu           ");
    sb.append("=============================================================");
    sb.append("1. Enter Feedback");
    sb.append("9. To return to previous Menu");
    return sb.toString();
  }
  public String viewFeedbackPage() {
    StringBuilder sb=new StringBuilder();
    sb.append("=============================================================");
    sb.append("                      Feedback Menu           ");
    sb.append("=============================================================");
    sb.append("No new feedbacks, you must have read every feedback");
    sb.append("1. to return back to previous menu");
    return sb.toString();
  }
}
