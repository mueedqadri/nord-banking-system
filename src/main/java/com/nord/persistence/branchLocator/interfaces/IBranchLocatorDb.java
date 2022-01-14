package com.nord.persistence.branchLocator.interfaces;

import java.util.List;

/**
 * @author Jay Nimeshkumar Patel
 */
public interface IBranchLocatorDb {

  /**
   * This method fetches Cities from database
   * @return A list of IBranchLocator Object with city details
   */
  List<IBranchLocatorModel> getCities();

  /**
   * This method fetches Bank branches in the selected city from the database
   * @return A list of IBranchLocator Object with details of bank branches
   */
  List<IBranchLocatorModel> locateBranch(int userInput);
}
