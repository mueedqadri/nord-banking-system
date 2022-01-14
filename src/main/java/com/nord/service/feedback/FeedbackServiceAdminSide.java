package com.nord.service.feedback;

import com.nord.service.feedback.interrfaces.IFeedbackFactory;
import com.nord.service.feedback.interrfaces.IFeedbackServiceAdminSide;
import com.nord.view.feedback.interfaces.IFeedbackAdminSideView;
import java.util.List;

/**
 * This represents Feedback service from admin side
 * It has method to show feedbacks to the admin
 * @author Harit Patwa
 */
public class FeedbackServiceAdminSide implements IFeedbackServiceAdminSide {
  private IFeedbackFactory feedbackFactory;
  private List<String> feedbackList;
  private IFeedbackAdminSideView view;

  public void showFeedbacks() {
    while (true) {
      view.showHeader();
      feedbackList = feedbackFactory.getFeedbackModel().getFeedback();
      feedbackFactory.getFeedbackModel().setFeedbackStatus();
      if(! feedbackList.isEmpty()){
        view.viewFeedbacks(feedbackList);
      }else {
        view.noNewFeedbacks();
      }
      view.showReturnMenuWithNumber(feedbackList.size() + 1);
      int input = view.getIntegerInput();
      if (input != (feedbackList.size() + 1)) {
        while (input != 9) {
          view.showInvalidInput();
          view.showReturnMenu();
          input = view.getIntegerInput();
        }
      }
      return;
    }
  }

  @Override
  public IFeedbackServiceAdminSide setView(IFeedbackAdminSideView feedbackAdminSideView) {
    this.view=feedbackAdminSideView;
    return this;
  }

  @Override
  public IFeedbackServiceAdminSide setFactory(IFeedbackFactory feedbackFactory) {
    this.feedbackFactory =feedbackFactory;
    return this;
  }

}
