package com.nord.service.profileManagement;

import com.nord.common.Constants;
import com.nord.service.profileManagement.interfaces.IProfileManagementFactory;
import com.nord.service.profileManagement.interfaces.IProfileManagementActions;
import com.nord.common.Utils;
import com.nord.view.profileManagement.interfaces.IProfileManagementView;

/**
 * Service class which performs profile management operations
 * @author Jay Nimeshkumar Patel, Rahul Reddy Puchakayala
 */
public class ProfileManagementActions implements IProfileManagementActions {

  private IProfileManagementFactory factory;
  private IProfileManagementView profileView;


  @Override
  public ProfileManagementActions setFactory(IProfileManagementFactory factory) {
    this.factory = factory;
    return this;
  }

  @Override
  public ProfileManagementActions setView(IProfileManagementView profileView) {
    this.profileView = profileView;
    return this;
  }

  @Override
  public boolean deactivateAccount() {
    profileView.areYouSure();
    String confirmation = profileView.getStringInputNoValidation();
    if(confirmation.equalsIgnoreCase(Constants.YES_Y)) {
      boolean result = factory.profileManagementAction().deactivateAccount();
      if (result) {
        profileView.accountDeleted();
        return true;
      } else {
        profileView.notDeleted();
        return false;
      }
    } else if (confirmation.equalsIgnoreCase(Constants.NO_N)) {
      return false;
    } else {
      profileView.showInvalidInput();
      return false;
    }
  }

  @Override
  public boolean modifyUserPassword() {
    profileView.reEnterPasswordMessage();
    String enteredPassword = profileView.getStringInputNoValidation();
    String userName = factory.profileManagementAction().getUsernameById();
    String hashPassword = factory.userAuthModel().getHashPassword(userName, false);
    if (hashPassword.equals(Utils.encryptToSha256(enteredPassword))) {
      profileView.enterNewPasswordMenu();
      enteredPassword = profileView.getStringInputNoValidation();
      String hashedPassword = Utils.encryptToSha256(enteredPassword);
      boolean result = factory.profileManagementAction().changePassword(hashedPassword);
      if (result) {
        profileView.passwordChangeSuccess();
        return true;
      }
    }
    profileView.passwordChangeFailure();
    return false;
  }
}
