package com.nord.service.bankingTransactions;

import com.nord.persistence.bankingTransactions.PayeeDetailsModel;
import com.nord.persistence.bankingTransactions.interfaces.IPayeeDetailsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock class for customerPayee persistence class
 */
public class CustomerPayeeMock {

  public List<Object> addNewPayee() {
    Object accountNo = "9876543210";
    List<Object> payeeValues = new ArrayList<>();
    payeeValues.add("Captain");
    payeeValues.add("America");
    payeeValues.add(Long.parseLong(accountNo.toString()));
    payeeValues.add("Cap");
    return payeeValues;
  }

  public IPayeeDetailsModel getPayeeDetailsMock() {
    IPayeeDetailsModel payeeDetailsModel = new PayeeDetailsModel();
    payeeDetailsModel.setPayeeId(1);
    payeeDetailsModel.setUserId(1);
    payeeDetailsModel.setFirstName("Test");
    payeeDetailsModel.setLastName("Payee");
    payeeDetailsModel.setAccountNo(1234563980);
    payeeDetailsModel.setNickName("Testing");
    return payeeDetailsModel;
  }

  public boolean editPayeeDetailsMock() {
    IPayeeDetailsModel payeeDetailsModel = new PayeeDetailsModel();
    payeeDetailsModel.setPayeeId(1);
    payeeDetailsModel.setUserId(1);
    payeeDetailsModel.setFirstName("Test");
    payeeDetailsModel.setLastName("Payee");
    payeeDetailsModel.setAccountNo(1234563980);
    payeeDetailsModel.setNickName("Edit Testing");
    return true;
  }
}
