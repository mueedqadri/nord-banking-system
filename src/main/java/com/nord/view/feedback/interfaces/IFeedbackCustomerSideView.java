package com.nord.view.feedback.interfaces;

import com.nord.view.interfaces.IUserInterface;

public interface IFeedbackCustomerSideView extends IUserInterface {

  void showHeader();
  void showMenu();
  void showReturnMenu();
  void showCongratulationsMenu();
  void showInvalidInput();
  int getIntegerInput();
  void enterFeedback();
  String getStringLineInput();
  void showInvalidInputNumber();
}
