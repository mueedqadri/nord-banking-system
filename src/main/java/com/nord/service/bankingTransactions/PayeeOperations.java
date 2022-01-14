package com.nord.service.bankingTransactions;

import com.nord.common.ColumnNames;
import com.nord.common.Constants;
import com.nord.persistence.bankingTransactions.interfaces.IPayeeDetailsModel;
import com.nord.service.bankingTransactions.interfaces.IPayeeOperations;
import com.nord.service.bankingTransactions.interfaces.ITransactionsFactory;
import com.nord.view.bankingTransactions.interfaces.IPayeesView;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * service class which performs all payee related operations
 * @author Rahul Reddy Puchakayala
 */
public class PayeeOperations extends AbstractPayee implements IPayeeOperations {

  private ITransactionsFactory factory;
  private IPayeesView payeesView;

  public PayeeOperations setFactory(ITransactionsFactory factory) {
    this.factory = factory;
    return this;
  }

  public PayeeOperations setPayeeView(IPayeesView payeeView) {
    this.payeesView = payeeView;
    return this;
  }

  private Map<String, Object> preparePayeeDetails() {
    return new LinkedHashMap<String, Object>() {{
      put(Constants.FIRST_NAME, null);
      put(Constants.LAST_NAME, null);
      put(Constants.ACCOUNT_NO_VALID, null);
      put(Constants.NICK_NAME, null);
    }};
  }

  public void addPayee() {
    payeesView.showHeader();
    Map<String, Object> newPayeeDetails = preparePayeeDetails();
    payeesView.addNewPayee(newPayeeDetails);
    payeesView.selectConfirmation();
    String input = payeesView.getStringInput();
    if (input.equalsIgnoreCase(Constants.YES_Y)) {
      factory.getPayeesForCustomer().addPayee(newPayeeDetails);
      payeesView.payeeAddedSuccessfully();
    } else if(!input.equalsIgnoreCase(Constants.NO_N)){
      payeesView.showInvalidInput();
    }
  }

  public void manageCustomerPayees() {
    payeesView.managePayeeHeader();
    IPayeeDetailsModel selectedPayee = selectPayee(payeesView, factory.getPayeesForCustomer().getPayeeDetails());
    if(selectedPayee != null){
      payeesView.managePayeeMenuOptions();
      int userInput = payeesView.getIntegerInput();
      switch (userInput) {
        case 1:
          boolean result = factory.getPayeesForCustomer().deletePayee(selectedPayee.getPayeeId());
          if (result) {
            payeesView.showDeletedPayeeMessage();
          }
          break;
        case 2:
          if (editPayeeDetails(selectedPayee.getPayeeId())) {
            payeesView.updateDetailsSuccessMessage();
          } else {
            payeesView.errorMessage();
            manageCustomerPayees();
          }
          break;
        case 3:
          return;
        default:
          payeesView.showInvalidInput();
          manageCustomerPayees();
          break;
      }
    }
  }

  public boolean editPayeeDetails(int payeeIndex) {
    Object updatedValue;
    payeesView.editPayeeDetailsMenuOptions();
    int userInput = payeesView.getIntegerInput();
    switch (userInput) {
      case 1:
        payeesView.editPayeeEnterDetails(Constants.FIRST_NAME);
        updatedValue = payeesView.getStringInput();
        return factory.getPayeesForCustomer().editPayeeDetails(payeeIndex, updatedValue, ColumnNames.FIRST_NAME);
      case 2:
        payeesView.editPayeeEnterDetails(Constants.ACCOUNT_NO_VALID);
        updatedValue = payeesView.getValidAccountNo();
        return factory.getPayeesForCustomer().editPayeeDetails(payeeIndex, updatedValue, ColumnNames.ACCOUNT_NO);
      case 3:
        payeesView.editPayeeEnterDetails(Constants.NICK_NAME);
        updatedValue = payeesView.getStringInput();
        return factory.getPayeesForCustomer().editPayeeDetails(payeeIndex, updatedValue, ColumnNames.NICK_NAME);
      case 4:
        return false;
      default:
        payeesView.showInvalidInput();
        editPayeeDetails(payeeIndex);
        break;
    }
    return false;
  }
}
