package com.nord.service.feedback;

import com.nord.service.feedback.interrfaces.IFeedbackFactory;
import com.nord.service.feedback.interrfaces.IFeedbackServiceCustomerSide;
import com.nord.view.feedback.interfaces.IFeedbackCustomerSideView;

/**
 * This class represents Feedback service feature from customer side,
 * it has methods to post feedbacks
 * @author Harit Patwa
 */
public class FeedbackServiceCustomerSide implements IFeedbackServiceCustomerSide {

  private IFeedbackFactory feedbackFactory;
  private IFeedbackCustomerSideView view;

  public IFeedbackServiceCustomerSide setFeedbackFactory(IFeedbackFactory feedbackFactory) {
    this.feedbackFactory = feedbackFactory;
    return this;
  }

  public IFeedbackServiceCustomerSide setView(IFeedbackCustomerSideView view) {
    this.view = view;
    return this;
  }

  public void feedbackHomePage() {

      view.showHeader();
      view.showMenu();
      view.showReturnMenu();
      int input = view.getIntegerInput();
      while (true) {
        switch (input) {
          case 1:
            view.enterFeedback();
            String feedback = view.getStringLineInput();
            feedbackFactory.getFeedbackModel().setFeedback(feedback);
            view.showCongratulationsMenu();
            view.showReturnMenu();
            input = view.getIntegerInput();
            while (input != 9) {
              view.showInvalidInput();
              view.showReturnMenu();
              input = view.getIntegerInput();
            }
            return;
          case 9:
            return;
          default:
            view.showInvalidInputNumber();
            view.showReturnMenu();
            input=view.getIntegerInput();
        }
      }

  }
}



