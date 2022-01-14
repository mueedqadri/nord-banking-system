package com.nord.view.interfaces;

import java.time.LocalDate;

public interface IUserInterface {

  int getValidPhoneNo();

  long getValidAccountNo();

  int getIntegerInput();

  String getStringInput();

  String getStringLineInput();

  LocalDate getDateInputLessThanToday();

  int getValidAge();

  int getValidSalary();

  int getvalidSpending();

  void showInvalidAge();

  void showInvalidSalary();

  void showInvalidSpending();

  String getValidEmailInput();

  String getStringInputNoValidation();

  void showDateInput();

  LocalDate getValidDateInput();

  LocalDate getDateInputGreaterThanToday();

  void showInvalidInputString();

  void showInvalidDateGreaterThanToday();

  void showInvalidInputNumber();

  void showInvalidDate();

  void showInvalidEmail();

  void showInvalidDateLessThanToday();

  void fieldCannotBeEmpty();

  double getDoubleInput();

  void showInvalidDoubleInput();

  String getInput();
}
