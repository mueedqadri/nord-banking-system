package com.nord.service.feedback.interrfaces;

import com.nord.view.feedback.interfaces.IFeedbackCustomerSideView;

public interface IFeedbackServiceCustomerSide {

  /**
   * Main page of feedback feature provides navigation to user
   * @param
   */
  void feedbackHomePage();

  IFeedbackServiceCustomerSide setView(IFeedbackCustomerSideView feedbackCustomerSideView);
}
