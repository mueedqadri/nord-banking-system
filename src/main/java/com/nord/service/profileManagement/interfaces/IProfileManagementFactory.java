package com.nord.service.profileManagement.interfaces;

import com.nord.persistence.profileManagement.interfaces.IProfileManagement;
import com.nord.persistence.userManagement.UserAuthDb;

/**
 * Interface which is implemented by ProfileManagementFactory class
 * @author Rahul Reddy Puchakayala, Jay Nimeshkumar Patel
 */
public interface IProfileManagementFactory {

  IProfileManagement profileManagementAction();

  UserAuthDb userAuthModel();

  boolean deactivateUserAccount();

  boolean changeUserPassword();
}
