package com.nord.view.bankingTransactions;

import com.nord.persistence.bankingTransactions.interfaces.IPayeeDetailsModel;
import com.nord.view.AbstractUserInterface;
import com.nord.view.bankingTransactions.interfaces.IPayeesView;

import java.util.Locale;
import java.util.Map;

/**
 * This class is responsible for the presentation layer of banking transaction feature
 * @author Jay Nimeshkumar Patel, Rahul Reddy Puchakayala
 */
public class PayeesView extends AbstractUserInterface implements IPayeesView {

  @Override
  public void payeeMenuOptions() {
    System.out.println("=============================================================");
    System.out.println("                   Add or Manage Payees            ");
    System.out.println("=============================================================\n");

    System.out.println("1. Add Payee");
    System.out.println("2. Manage Payee");
    System.out.println("3. Back to previous Menu");
    System.out.print("\nEnter your selection: \n");
  }

  @Override
  public Map<String, Object> addNewPayee(Map<String, Object> payeeDetails) {

    for (Map.Entry<String, Object> item : payeeDetails.entrySet()) {
      System.out.println("Enter " + item.getKey() + " *");
      if (item.getKey().toLowerCase(Locale.ROOT).contains("account number")) {
        item.setValue(getValidAccountNo());
      } else {
        item.setValue(getStringInput());
      }
    }
    return payeeDetails;
  }

  @Override
  public void showHeader() {
    System.out.println("=============================================================");
    System.out.println("                         Add Payee            ");
    System.out.println("=============================================================");
  }

  @Override
  public void managePayeeHeader() {
    System.out.println("=============================================================");
    System.out.println("                       Manage Payee            ");
    System.out.println("=============================================================\n");

    System.out.println("List of Payees:");
  }

  @Override
  public void selectConfirmation() {
    System.out.println("Do you want to continue (Y/N):");
  }

  @Override
  public void showPayeesList(int index, String firstName) {
    System.out.println(index + ". " + firstName);
  }

  @Override
  public void listOfPayeesData(IPayeeDetailsModel model) {
    System.out.println(model.getFirstName() + "'s Details:\n" + "Full Name: " + model.getFirstName() + " " + model.getLastName() +
            "\nAccount Number: " + model.getAccountNo() + "\nNick Name: " + model.getNickName());
  }

  @Override
  public void managePayeeMenuOptions() {
    System.out.println("\n1. Delete Payee\n2. Edit Payee details\n3. Back to previous Menu");
    System.out.println("Enter your selection:");
  }

  @Override
  public void editPayeeDetailsMenuOptions() {
    System.out.println("=============================================================");
    System.out.println("                       Edit Payee Details           ");
    System.out.println("=============================================================\n");

    System.out.println("1. Change Name\n2. Change Account Number\n3. Change Nick Name\n4. Back to Previous Menu");
    System.out.println("Enter your choice: ");
  }

  @Override
  public void payeeAddedSuccessfully() {
    System.out.println("Payee added successfully!\n");
  }

  @Override
  public void showAddPayeeError() {
    System.out.println("Error while adding payee details!, Please try again\n");
  }

  public void displayEmptyPayeeMessage() {
    System.out.println("Payees do not exist!");
  }

  public void showDeletedPayeeMessage() {
    System.out.println("Successfully removed payee!\n");
  }

  public void updateDetailsSuccessMessage() {
    System.out.println("Details updated successfully!\n");
  }

  public void editPayeeEnterDetails(String keyword) {
    System.out.println("Enter new " + keyword + ":");
  }

  public void errorMessage() {
    System.out.println("Something went wrong, please try again");
  }

  @Override
  public void showInvalidInput() {
    System.out.println("Please enter a valid input!\n");
  }

  public int selectPayee() {
    System.out.println("\nSelect the payee or 0 to exit:");
    return getIntegerInput();
  }

  public void selectValidPayee() {
    System.out.println("Please select the correct Payee index");
  }
}
