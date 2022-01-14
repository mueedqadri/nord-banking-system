package com.nord.service.feedback;

import com.nord.persistence.feedback.FeedbackModel;
import com.nord.persistence.feedback.interfaces.IFeedbackModel;
import com.nord.service.feedback.interrfaces.IFeedbackFactory;

/**
 * Factory class for Feedback feature, returns concrete objects
 * @author Harit Patwa
 */
public class FeedbackFactory implements IFeedbackFactory {

  @Override
  public IFeedbackModel getFeedbackModel() {
    return new FeedbackModel();
  }
}
