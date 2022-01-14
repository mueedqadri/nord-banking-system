package com.nord.persistence.bankingTransactions.interfaces;

import java.util.List;
import java.util.Map;

/**
 * This interface provides implementation for CustomerPayee class
 * @author Rahul Reddy Puchakayala
 */
public interface ICustomerPayees {

  /**
   * It fetches all the payees associated with the current logged in user
   * @return list with all the payee details
   */
  List<IPayeeDetailsModel> getPayeeDetails();

  /**
   * This method inserts a new payee record with all the values from payeeDetails map
   * @param payeeDetails hashmap with key as column name and object as the associated value
   * @return true if payee is added, false if any exception occurs
   */
  boolean addPayee(Map<String, Object> payeeDetails);

  /**
   * This method removes the payee record of provided payee index
   * @param payeeIndex id of payee to be deleted
   * @return true if payee is deleted successfully, false otherwise
   */
  boolean deletePayee(int payeeIndex);

  /**
   * This method updates the required field in payee details table with the provided value
   * @param payeeIndex id of the payee to be edited
   * @param newValue value to be updated
   * @param type column name for which a newValue gets updated
   * @return
   */
  boolean editPayeeDetails(int payeeIndex, Object newValue, String type);
}
