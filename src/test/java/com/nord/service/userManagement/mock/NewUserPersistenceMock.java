package com.nord.service.userManagement.mock;

import com.nord.persistence.userManagement.interfaces.ICustomerDetailsPersistence;

import java.util.Map;

/**
 * Mock persistence for Creating new user
 * @author Abdul Mueed Qadri
 */

public class NewUserPersistenceMock implements ICustomerDetailsPersistence {
  private int userId;

  public NewUserPersistenceMock setUserId(int userId) {
    this.userId = userId;
    return this;
  }

  @Override
  public boolean addNewCustomer(Map<String, Object> customerDetails, boolean isAdmin) {
    if (!customerDetails.isEmpty() ){
      return true;
    }
    return false;
  }

  @Override
  public int getUserId(String username) {
    return userId;
  }
}
