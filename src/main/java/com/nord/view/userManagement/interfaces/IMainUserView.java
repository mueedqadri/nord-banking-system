package com.nord.view.userManagement.interfaces;

/**
 * Generic Interface to display views of the following format
 * @author Abdul Mueed Qadri
 */
public interface IMainUserView extends IUserAuthView {

  void showMenu();

  void showHeader();

  void showInvalidInput();
}
