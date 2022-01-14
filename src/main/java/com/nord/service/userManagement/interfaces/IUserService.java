package com.nord.service.userManagement.interfaces;

/**
 * Generic Interface implemented by User Services (Customer and Admin) to
 * perform login and display homepage
 * @author Abdul Mueed Qadri
 */
public interface IUserService  {

  /**
   * This method displays the respective home page to the specific user
   */
  void homePage();

  /**
   * validates the user by validating the encrypted password
   * @return true if the user is successfully validated else return false
   */
  boolean login();
}
