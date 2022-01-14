package com.nord.view.profileManagement;

import com.nord.view.AbstractUserInterface;
import com.nord.view.creditCardServicesAndRecommendation.CreditCardTypesView;
import com.nord.view.profileManagement.interfaces.IProfileManagementView;

/**
 * This class is responsible for the presentation layer of Profile Management
 * @author Jay Nimeshkumar Patel, Rahul Reddy Puchakayala
 */
public class ProfileManagementView extends AbstractUserInterface implements IProfileManagementView {

  @Override
  public void showHeader() {
    System.out.println("=============================================================");
    System.out.println("                   User Profile Management            ");
    System.out.println("=============================================================");
  }

  @Override
  public void showMenu() {
    System.out.println("\n1. Deactivate Your Account");
    System.out.println("2. Change Account Password");
    System.out.println("3. Exit");

    System.out.println("Enter your selection: ");
  }

  @Override
  public void showInvalidInput() {
    System.out.println("Invalid selection! Please select a valid option:");
  }

  @Override
  public void accountDeleted() {
    System.out.println("Account Successfully Deleted!");
  }

  @Override
  public void notDeleted() {
    System.out.println("Account deletion wasn't completed");
  }

  @Override
  public void reEnterPasswordMessage() {
    System.out.println("Please enter your current password to proceed:");
  }

  @Override
  public void enterNewPasswordMenu() {
    System.out.println("Please enter your new password:");
  }

  @Override
  public void passwordChangeSuccess() {
    System.out.println("Your password has been updated successfully!");
  }

  @Override
  public void passwordChangeFailure() {
    System.out.println("Something went wrong! Please try again");
  }

  @Override
  public void areYouSure() {
    System.out.println("Are you sure that you want to delete your account? Y/N");
  }
}
