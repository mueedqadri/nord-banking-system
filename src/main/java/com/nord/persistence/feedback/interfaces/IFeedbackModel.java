package com.nord.persistence.feedback.interfaces;

import java.util.List;

public interface IFeedbackModel {

  /**
   * sets the feedback according to userId
   * @param feedbackText
   * @return
   */
  boolean setFeedback(String feedbackText);
  /**
   * Returns List object of Feebacks
   * @return
   */
  List<String> getFeedback();
  boolean setFeedbackStatus();

}
