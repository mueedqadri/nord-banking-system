package com.nord.service.profileManagement;

import com.nord.service.profileManagement.interfaces.IProfileManagementFactory;
import com.nord.service.profileManagement.ProfileManagementFactory;
import com.nord.common.Utils;

/**
 * Mock class for ProfileManagement class
 */
public class ProfileManagementMock {

  IProfileManagementFactory factory;

  public boolean changePasswordMock() {
    String password = "ravi123";
    String currentHashPassword = Utils.encryptToSha256(password);
    return currentHashPassword.equals(getHashPasswordMock());
  }

  public String getHashPasswordMock() {
    String userName = "ravi@gmail.com";
    factory = new ProfileManagementFactory();
    String hashPassword = factory.userAuthModel().getHashPassword(userName, false);
    if (!hashPassword.isEmpty()) {
      return hashPassword;
    }
    return null;
  }

}
