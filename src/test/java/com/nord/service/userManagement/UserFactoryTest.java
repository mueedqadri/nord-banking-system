package com.nord.service.userManagement;

import com.nord.service.userManagement.interfaces.IUserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

  /**
   * Test object creation in factory
   * @author Abdul Mueed Qadri
   */

  IUserFactory factory = new UserFactory();
  @Test
  void createCustomer() {
    assertNotNull(factory.createCustomer());
  }

  @Test
  void createAdmin() {
    assertNotNull(factory.createAdmin());
  }

  @Test
  void createNewCustomer() {
    assertNotNull(factory.createNewCustomer());
  }

  @Test
  void createNewEmployee() {
    assertNotNull(factory.createNewEmployee());
  }

  @Test
  void createFeedbackAdminService() {
    assertNotNull(factory.createFeedbackAdminService());
  }

  @Test
  void createBranchLocator() {
    assertNotNull(factory.createBranchLocator());
  }

  @Test
  void createLoanApproval() {
    assertNotNull(factory.createLoanApproval());
  }
}