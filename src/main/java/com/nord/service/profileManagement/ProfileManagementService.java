package com.nord.service.profileManagement;

import com.nord.service.Context;
import com.nord.service.profileManagement.interfaces.IProfileManagementFactory;
import com.nord.service.profileManagement.interfaces.IProfileManagementService;
import com.nord.view.profileManagement.interfaces.IProfileManagementView;

/**
 * This is service class which provides main menu for  Profile Management
 * @author Rahul Reddy Puchakayala, Jay Nimeshkumar Patel
 */
public class ProfileManagementService implements IProfileManagementService {

  private IProfileManagementView profileView;
  private IProfileManagementFactory factory;

  public ProfileManagementService() {
  }

  public ProfileManagementService setFactory(IProfileManagementFactory profileManagementFactory) {
    this.factory = profileManagementFactory;
    return this;
  }

  public ProfileManagementService setView(IProfileManagementView profileView) {
    this.profileView = profileView;
    return this;
  }

  public void menuOptions() {
    int userInput;
    profileView.showHeader();
    do {
      profileView.showMenu();
      userInput = profileView.getIntegerInput();
      switch (userInput) {
        case 1:
          if(factory.deactivateUserAccount()){
            Context.setLoggedInUserId(0);
            return;
          }
          break;
        case 2:
          factory.changeUserPassword();
          break;
        case 3:
          factory.profileManagementAction().closeDbConnection();
          return;
        default:
          profileView.showInvalidInput();
      }
    } while (true);
  }
}
