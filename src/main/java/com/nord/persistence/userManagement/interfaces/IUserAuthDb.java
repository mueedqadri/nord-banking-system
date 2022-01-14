package com.nord.persistence.userManagement.interfaces;

/**
 * Interface which enables authentication when implemented
 * @author Abdul Mueed Qadri
 */
public interface IUserAuthDb {

  /**
   * Fetches the password for the user
   * @param userName of the user for which we need to get the password
   * @param isAdmin checks if the user is admin or not
   * @return hash password
   */
  String getHashPassword(String userName, boolean isAdmin);

  /**
   * Get the user id of the user
   * @param username of the user
   * @return userid
   */
  int getUserId(String username);

  /**
   * Check if the user is a new user or not
   * @param username to check for new user
   * @return true if new user else return false
   */
  boolean isNewUser(String username);

  /**
   * Set the password of the new user
   * @param password new password
   * @param username of the logged in user
   * @return true if set successfully else false
   */
  boolean setPassword(String password, String username);
}
