package com.nord.service.branchLocator;

import com.nord.persistence.branchLocator.interfaces.IBranchLocatorDb;
import com.nord.persistence.branchLocator.interfaces.IBranchLocatorModel;
import com.nord.service.branchLocator.interfaces.IBranchLocator;
import com.nord.service.userManagement.interfaces.IUserFactory;
import com.nord.view.branchLocator.interfaces.IBranchLocatorView;

import java.util.List;

/**
 * This class represents locating nearest branch to the user
 * @author Jay Nimeshkumar Patel
 */
public class BranchLocatorService implements IBranchLocator {

  private IUserFactory factory;
  private IBranchLocatorView view;
  private IBranchLocatorDb db;
  private List<IBranchLocatorModel> branchList;
  private List<IBranchLocatorModel> cityList;

  public BranchLocatorService setDb(IBranchLocatorDb db) {
    this.db = db;
    return this;
  }

  public BranchLocatorService setView(IBranchLocatorView view) {
    this.view = view;
    return this;
  }

  public BranchLocatorService setFactory(IUserFactory factory) {
    this.factory = factory;
    return this;
  }

  public boolean menuOptions() {
    view.showHeader();
    do {
      cityList = db.getCities();
      int exit = view.showMenu(cityList);
      int userInput = view.getIntegerInput();
      if (userInput < exit) {
        branchList = db.locateBranch(userInput);
        view.viewBranches(branchList);
      } else {
        if (userInput == exit) {
          return true;
        } else {
          view.showInvalidInput();
        }
      }
    } while (true);
  }
}

