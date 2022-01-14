package com.nord.view.bankingTransactions.interfaces;

import com.nord.persistence.bankingTransactions.interfaces.IPayeeDetailsModel;
import com.nord.view.interfaces.IUserInterface;

import java.util.Map;

public interface IPayeesView extends IUserInterface {

  void payeeMenuOptions();

  Map<String, Object> addNewPayee(Map<String, Object> payeeDetails);

  void showHeader();

  void managePayeeHeader();

  void showPayeesList(int index, String firstName);

  void listOfPayeesData(IPayeeDetailsModel model);

  void managePayeeMenuOptions();

  void editPayeeDetailsMenuOptions();

  void showAddPayeeError();

  int selectPayee();

  void displayEmptyPayeeMessage();

  void selectValidPayee();

  void showDeletedPayeeMessage();

  void updateDetailsSuccessMessage();

  void editPayeeEnterDetails(String keyword);

  void errorMessage();

  void showInvalidInput();

  void selectConfirmation();

  void payeeAddedSuccessfully();
}
