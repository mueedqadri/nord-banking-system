package com.nord.service.userManagement.interfaces;

/**
 * Interface implemented by NewUser
 * @author Abdul Mueed Qadri
 */
public interface INewUser {

  /**
   * Both the new customer and new employees are created by this method
   * @return true if the user is created successfully else return false
   */
  boolean createNewUserAccount();
}
