package com.nord.service.feedback.interrfaces;


import com.nord.view.feedback.interfaces.IFeedbackCustomerSideView;

public interface IEnterFeedback {

  /**
   * Takes string as an input stores it into database
   * @param stringLineInput
   * @return
   */
  boolean enterFeedback(String stringLineInput);
  IEnterFeedback setView(IFeedbackCustomerSideView view);
  IEnterFeedback setFactory(IFeedbackFactory feedbackFactory);
}
