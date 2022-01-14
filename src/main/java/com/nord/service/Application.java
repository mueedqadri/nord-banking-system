package com.nord.service;

import com.nord.persistence.DbConnection;
import com.nord.service.interfaces.IApplication;
import com.nord.service.userManagement.interfaces.IUserFactory;
import com.nord.service.userManagement.interfaces.IUserService;
import com.nord.view.interfaces.IMenuInterface;

/**
 * Main application class that also acts as the home page for all the user
 * clients
 * @author Abdul Mueed Qadri
 */
public class Application implements IApplication {

  private IMenuInterface view;

  private IUserFactory factory;

  public Application setFactory(IUserFactory factory) {
    this.factory = factory;
    return this;
  }

  public Application setView(IMenuInterface view) {
    this.view = view;
    return this;
  }

  @Override
  public void mainMenu(){
    do {
      view.showMenu();
      int input = view.getIntegerInput();
      switch (input) {
        case 1:
          handleUser(factory.createCustomer());
          break;
        case 2:
          handleUser(factory.createAdmin());
          break;
        case 3:
          factory.createBranchLocator().menuOptions();
          break;
        case 4:
          DbConnection.closeDbConnection();
          return;
        default:
          view.showInvalidInputNumber();
          break;
      }
    }while (true);
  }

  private void handleUser(IUserService user) {
    boolean isValidUser = true;
    do {
      if(!isValidUser){
        int input = view.getIntegerInput();
        if (input == 0){
          return;
        }
      }
      isValidUser = user.login();
    } while (!isValidUser);
    user.homePage();
  }
}

