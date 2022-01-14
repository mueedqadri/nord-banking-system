package com.nord.view.profileManagement.interfaces;

import com.nord.view.interfaces.IUserInterface;

public interface IProfileManagementView extends IUserInterface {

  void showHeader();

  void showMenu();

  void showInvalidInput();

  void accountDeleted();

  void notDeleted();

  void reEnterPasswordMessage();

  void enterNewPasswordMenu();

  void passwordChangeSuccess();

  void passwordChangeFailure();

  void areYouSure();
}
