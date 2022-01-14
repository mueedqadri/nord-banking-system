package com.nord.view.branchLocator.interfaces;

import com.nord.persistence.branchLocator.interfaces.IBranchLocatorModel;
import com.nord.view.interfaces.IUserInterface;

import java.util.List;

public interface IBranchLocatorView extends IUserInterface {

  void showHeader();

  int showMenu(List<IBranchLocatorModel> cities);

  void viewBranches(List<IBranchLocatorModel> branches);

  void showInvalidInput();
}
