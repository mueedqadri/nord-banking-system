package com.nord.persistence.profileManagement.interfaces;

/**
 * This interface is implemented by ProfileManagement class
 * @author Jay Nimeshkumar Patel, Rahul Reddy Puchakayala
 */
public interface IProfileManagement {

  /**
   * deactivates the current user's account
   * @return true if deactivation is successful, false otherwise
   */
  boolean deactivateAccount();

  /**
   * Provides the email address of current logged in
   * @return String representing the email address
   */
  String getUsernameById();

  /**
   * updates the user's password with the provided hash password
   * @param hashedPassword hash of user provided string value
   * @return true if operation is success, false otherwise
   */
  boolean changePassword(String hashedPassword);

  /**
   * closes the database connection object
   */
  void closeDbConnection();
}
