package com.nord.service.bankingTransactions.interfaces;

import com.nord.service.bankingTransactions.PayeeOperations;
import com.nord.view.bankingTransactions.interfaces.IPayeesView;

/**
 * Interface which is implemented by PayeeOperations class
 * @author Rahul Reddy Puchakayala
 */
public interface IPayeeOperations {

  PayeeOperations setFactory(ITransactionsFactory factory);

  PayeeOperations setPayeeView(IPayeesView payeeView);

  /**
   * adds a new payee to the current user
   */
  void addPayee();

  /**
   * provides menu to edit and delete the selected payee
   */
  void manageCustomerPayees();

  /**
   * Provides the option to update the first name, account numer and nick name of the payee
   * @param payeeIndex id of the payee whose details are to be edited
   * @return true if the payee details gets updated, false otherwise
   */
  boolean editPayeeDetails(int payeeIndex);
}
