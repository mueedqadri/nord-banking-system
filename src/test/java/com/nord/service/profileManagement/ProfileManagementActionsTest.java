package com.nord.service.profileManagement;

import com.nord.service.Context;
import com.nord.service.profileManagement.ProfileManagementMock;
import com.nord.service.profileManagement.interfaces.IProfileManagementFactory;
import com.nord.service.profileManagement.ProfileManagementFactory;
import com.nord.service.profileManagement.ProfileManagementService;
import com.nord.view.profileManagement.ProfileManagementView;
import com.nord.view.profileManagement.interfaces.IProfileManagementView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ProfileManagementActions service class
 */
class ProfileManagementActionsTest {

  private final int TEST_USER_ID = 1;
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void deactivateAccountTestTrue() {
    String input = "4 y";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    Context.setLoggedInUserId(TEST_USER_ID);
    IProfileManagementFactory profileFactory = new ProfileManagementFactory();
    assertFalse(profileFactory.deactivateUserAccount());
  }

  @Test
  void deactivateAccountTestFalse() {
    String input = "4 n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    Context.setLoggedInUserId(0);
    IProfileManagementFactory profileFactory = new ProfileManagementFactory();
    assertFalse(profileFactory.deactivateUserAccount());
  }

  @Test
  void profileManagementToMainMenuTest() {
    Context.setLoggedInUserId(TEST_USER_ID);

    String input = "3";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    IProfileManagementFactory profileFactory = new ProfileManagementFactory();
    IProfileManagementView profileView = new ProfileManagementView();
    ProfileManagementService profile = new ProfileManagementService()
        .setFactory(profileFactory)
        .setView(profileView);
    profile.menuOptions();
    String actualOutPut = outputStreamCaptor.toString().replace("\n", "").replace("\r", "");
    assertNotNull(actualOutPut);
  }

  @Test
  void modifyUserPasswordTestTrue() {
    Context.setLoggedInUserId(TEST_USER_ID);

    String input = "ravi123 ravi1234";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    IProfileManagementFactory profileFactory = new ProfileManagementFactory();
    ProfileManagementMock mock = new ProfileManagementMock();
    assertEquals(mock.changePasswordMock(), profileFactory.changeUserPassword());
  }

  @Test
  void modifyUserPasswordTestFalse() {
    Context.setLoggedInUserId(0);
    String input = "ravi1234";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    IProfileManagementFactory profileFactory = new ProfileManagementFactory();
    assertFalse(profileFactory.changeUserPassword());
  }

  @AfterEach
  public void tearDown() {
    IProfileManagementFactory profileFactory = new ProfileManagementFactory();
    profileFactory.profileManagementAction().closeDbConnection();
    System.setOut(standardOut);
  }
}