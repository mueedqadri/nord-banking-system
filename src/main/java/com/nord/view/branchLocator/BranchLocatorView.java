package com.nord.view.branchLocator;

import com.nord.common.Constants;
import com.nord.persistence.branchLocator.interfaces.IBranchLocatorModel;
import com.nord.view.AbstractUserInterface;
import com.nord.view.branchLocator.interfaces.IBranchLocatorView;

import java.util.List;

/**
 * This class is responsible for the presentation layer of Branch Locator
 * @author Jay Nimeshkumar Patel
 */
public class BranchLocatorView extends AbstractUserInterface implements IBranchLocatorView {

  @Override
  public void showHeader() {
    System.out.println("=============================================================");
    System.out.println("                       Branch Locator            ");
    System.out.println("=============================================================");
  }

  @Override
  public int showMenu(List<IBranchLocatorModel> cities) {
    System.out.println("Select the nearest city from your current location:");
    int count = 1;
    for (IBranchLocatorModel city : cities) {
      System.out.println(city.getCityId() + ". " + city.getCityName());
      count++;
    }
    System.out.println(count +". Exit");
    System.out.print("Enter your selection: \n");
    return count;
  }

  private void viewCity() {
    System.out.println("\nNearest Nord Bank branches is/are: \n");
    System.out.printf(Constants.FORMATTING_BRANCH_LOCATOR, "Number of Branches |", "Address                           |", "IFSC code       ");
    System.out.println();
    System.out.println("---------------------------------------------------------------------------------------------------");
  }

  @Override
  public void viewBranches(List<IBranchLocatorModel> branches) {
    viewCity();
    int count = 1;
    for (IBranchLocatorModel branch : branches) {
      viewBranch(count++, branch.getLocation(), branch.getIfsc());
    }
  }

  private void viewBranch(int branchCount, String location, String ifsc) {
    System.out.printf(Constants.FORMATTING_BRANCH_LOCATOR, branchCount + "         |", location + "    |", ifsc + "        ");
    System.out.println();
  }

  @Override
  public void showInvalidInput() {
    System.out.println("Please enter a valid input!\n");
  }
}
