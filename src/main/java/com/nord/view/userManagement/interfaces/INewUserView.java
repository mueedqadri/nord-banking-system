package com.nord.view.userManagement.interfaces;

import com.nord.view.interfaces.IUserInterface;

import java.util.Map;

/**
 * Interface for new user view
 * @author Abdul Mueed Qadri
 */
public interface INewUserView extends IUserInterface {

  Map<String, Object> addNewCustomer(Map<String, Object> customerDetails);

  void confirmDetails(Map<String, Object> customerDetails);

  void accountAddedSuccessfully();

  void showUserExist(String email);
}
