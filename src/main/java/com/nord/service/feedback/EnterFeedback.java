package com.nord.service.feedback;

import com.nord.service.feedback.interrfaces.IEnterFeedback;
import com.nord.service.feedback.interrfaces.IFeedbackFactory;
import com.nord.view.feedback.interfaces.IFeedbackCustomerSideView;

/**
 * This class is for users which helps user to enter feedbacks
 * @author Harit Patwa
 */
public class EnterFeedback implements IEnterFeedback {

  private IFeedbackCustomerSideView view;
  private IFeedbackFactory feedbackFactory;

  public boolean enterFeedback(String feedback) {
    feedbackFactory.getFeedbackModel().setFeedback(feedback);
    view.showCongratulationsMenu();
    view.showReturnMenu();
    int input = view.getIntegerInput();
    while (input != 9) {
      view.showInvalidInput();
      view.showReturnMenu();
      input = view.getIntegerInput();
    }
    return true;
  }

  @Override
  public IEnterFeedback setView(IFeedbackCustomerSideView view) {
    this.view = view;
    return this;
  }

  @Override
  public IEnterFeedback setFactory(IFeedbackFactory feedbackFactory) {
    this.feedbackFactory = feedbackFactory;
    return this;
  }
}
