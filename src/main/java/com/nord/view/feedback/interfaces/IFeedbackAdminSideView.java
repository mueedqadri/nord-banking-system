package com.nord.view.feedback.interfaces;

import java.util.List;

public interface IFeedbackAdminSideView {
  void showHeader();
  void showMenu();
  void showReturnMenu();
  void viewFeedbacks(List<String> feedbacks);
  void showInvalidInput();
  void noNewFeedbacks();
  void showReturnMenuWithNumber(int i);
  int getIntegerInput();
}
