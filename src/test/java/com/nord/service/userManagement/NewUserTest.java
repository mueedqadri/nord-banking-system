package com.nord.service.userManagement;

import com.nord.persistence.DbConnection;
import com.nord.persistence.userManagement.interfaces.ICustomerDetailsPersistence;
import com.nord.service.userManagement.interfaces.INewUser;
import com.nord.service.userManagement.mock.NewUserPersistenceMock;
import com.nord.service.userManagement.mock.UserManagementMockUser;
import com.nord.view.userManagement.NewUserView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test creation of new user
 * @author Abdul Mueed Qadri
 */

public class NewUserTest {

  private ICustomerDetailsPersistence persistence;
  private UserManagementMockUser mockUser;

  @BeforeEach
  public void setUp() {
    mockUser = new UserManagementMockUser();
  }

  @Test
  void createNewUserAccountTestValid() {
    persistence = new NewUserPersistenceMock()
            .setUserId(-1);
    StringBuilder sb = new StringBuilder();
    sb.append("User ");
    sb.append("Test ");
    sb.append("One ");
    sb.append("12/12/1999 ");
    sb.append("test@best.com ");
    sb.append("123123 ");
    sb.append("12 ");
    sb.append("TestCity ");
    sb.append("TestState ");
    sb.append("12AB2 ");
    sb.append("SA12311 ");
    sb.append("y ");
    sb.append("1 ");
    sb.append("0 ");
    InputStream in = new ByteArrayInputStream(sb.toString().getBytes());
    System.setIn(in);
    INewUser user = new NewUser()
            .setAdmin(false)
            .setPersistence(persistence)
            .setView(new NewUserView());
    assertTrue(user.createNewUserAccount());
  }

  @Test
  void createNewUserAccountTestInvalid() {
    persistence = new NewUserPersistenceMock()
            .setUserId(1);
    StringBuilder sb = new StringBuilder();
    sb.append("User ");
    sb.append("Test ");
    sb.append("One ");
    sb.append("12/12/1999 ");
    sb.append("test@best.com ");
    sb.append("123123 ");
    sb.append("12 ");
    sb.append("TestCity ");
    sb.append("TestState ");
    sb.append("12AB2 ");
    sb.append("SA12311 ");
    sb.append("y ");
    sb.append("0 ");
    InputStream in = new ByteArrayInputStream(sb.toString().getBytes());
    System.setIn(in);
    INewUser user = new NewUser()
            .setAdmin(false)
            .setPersistence(persistence)
            .setView(new NewUserView());
    assertFalse(user.createNewUserAccount());
  }

  @AfterEach
  public void tearDown() {
    DbConnection.closeDbConnection();
  }
}
