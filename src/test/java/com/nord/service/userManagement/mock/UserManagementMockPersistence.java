package com.nord.service.userManagement.mock;

import com.nord.persistence.userManagement.interfaces.IUserAuthDb;


/**
 * Mock persistence for handling user authentication
 * @author Abdul Mueed Qadri
 */

public class UserManagementMockPersistence implements IUserAuthDb {
  private String password;
  private String userName;
  private int userId;

  public UserManagementMockPersistence (UserManagementMockUser data){
    this.password = data.HASH_PASSWORD;
    this.userName = data.USERNAME;
    this.userId = data.USER_ID;
  }

  @Override
  public String getHashPassword(String userName, boolean isAdmin) {
    return password;
  }

  @Override
  public int getUserId(String username) {
    return userId;
  }

  @Override
  public boolean isNewUser(String username) {
    return false;
  }

  @Override
  public boolean setPassword(String password, String username) {
    return false;
  }
}
