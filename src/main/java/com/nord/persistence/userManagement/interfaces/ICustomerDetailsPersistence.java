package com.nord.persistence.userManagement.interfaces;

import java.util.Map;

/**
 * Interface that implemented by CustomerDetailsPersistence
 * @author Abdul Mueed Qadri
 */
public interface ICustomerDetailsPersistence {

  /**
   * Adds a new User to the DB
   * @param customerDetails Details of the user which is to be added
   * @param isAdmin Boolean to check if the user is an Admin or not
   * @return true if the operation is successful
   */
  boolean addNewCustomer(Map<String, Object> customerDetails, boolean isAdmin);

  /**
   * Retrieves the userId of the username
   * @param username username for which we need to get the userId
   * @return id of the user found else return -1
   */
  int getUserId(String username);
}
