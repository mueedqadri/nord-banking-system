package com.nord.service.userManagement;

import com.nord.persistence.DbConnection;
import com.nord.persistence.notification.NotificationDb;
import com.nord.persistence.userManagement.interfaces.IUserAuthDb;
import com.nord.service.userManagement.interfaces.IUserService;
import com.nord.service.userManagement.mock.UserManagementMockPersistence;
import com.nord.service.userManagement.mock.UserManagementMockUser;
import com.nord.view.userManagement.AdminMainMenuView;
import com.nord.view.userManagement.CustomerMainView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Login of a customer as well as admin
 * @author Abdul Mueed Qadri
 */

class UserAuthServiceTest {

  private IUserAuthDb persistence;
  private UserManagementMockUser mockUser;

  @BeforeEach
  public void setUp() {
    mockUser = new UserManagementMockUser();
    persistence = new UserManagementMockPersistence(mockUser);
  }

  @Test
  void loginValidCustomer() {
    String input = "rahul@canda.com rahul";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    IUserService customer = new CustomerService()
          .setNotification(new NotificationDb())
          .setView(new CustomerMainView())
          .setDbObj(persistence);
    assertTrue(customer.login());
  }

  @Test
  void loginInvalidCustomer(){
    String input = "rahul@canda.com rahu";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    IUserService customer = new CustomerService()
            .setNotification(new NotificationDb())
            .setView(new CustomerMainView())
            .setDbObj(persistence);
    assertFalse(customer.login());
  }


  @Test
  void loginValidAdmin() {
    String input = "rahul@canda.com rahul";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    IUserService admin = new AdminService()
            .setFactory(new UserFactory())
            .setView(new AdminMainMenuView())
            .setDbObj(persistence);
    assertTrue(admin.login());
  }

  @AfterEach
  public void tearDown() {
    DbConnection.closeDbConnection();
  }
}