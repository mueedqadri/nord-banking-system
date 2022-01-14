package com.nord.service.profileManagement;

import com.nord.persistence.profileManagement.ProfileManagement;
import com.nord.persistence.profileManagement.interfaces.IProfileManagement;
import com.nord.persistence.userManagement.UserAuthDb;
import com.nord.service.profileManagement.interfaces.IProfileManagementFactory;
import com.nord.view.profileManagement.ProfileManagementView;
import com.nord.view.profileManagement.interfaces.IProfileManagementView;

/**
 * This interface is implemented by ProfileManagementFactory class
 * @author Rahul Reddy Puchakayala, Jay Nimeshkumar Patel
 */
public class ProfileManagementFactory implements IProfileManagementFactory {

  private IProfileManagementView createProfileManagementView() {
    return new ProfileManagementView();
  }

  @Override
  public IProfileManagement profileManagementAction() {
    return new ProfileManagement();
  }

  @Override
  public UserAuthDb userAuthModel() {
    return new UserAuthDb();
  }

  @Override
  public boolean deactivateUserAccount() {
    return new ProfileManagementActions().setFactory(this).setView(createProfileManagementView()).deactivateAccount();
  }

  @Override
  public boolean changeUserPassword() {
    return new ProfileManagementActions().setFactory(this).setView(createProfileManagementView()).modifyUserPassword();
  }
}
