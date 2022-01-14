package com.nord.service.branchLocator;

import com.nord.persistence.DbConnection;
import com.nord.service.userManagement.UserFactory;
import com.nord.service.userManagement.interfaces.IUserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is a test for the BranchLocator feature
 * @author Jay Nimeshkumar Patel
 */
class BranchLocatorTest {

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void menuOptionsTest() {
    String input = "3 7";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    IUserFactory factory = new UserFactory();
    assertTrue(factory.createBranchLocator().menuOptions());
  }

  @Test
  void locateBranchTest() {
    String input = "6 7";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    IUserFactory factory = new UserFactory();
    factory.createBranchLocator().menuOptions();
    BranchLocatorMock mock = new BranchLocatorMock();
    String actualOutPut = outputStreamCaptor.toString();
    assertTrue(actualOutPut.contains(mock.locateBranch()));
  }

  @Test
  void locateCityTest() {
    String input = "3 7";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    IUserFactory factory = new UserFactory();
    factory.createBranchLocator().menuOptions();
    BranchLocatorMock mock = new BranchLocatorMock();
    String actualOutPut = outputStreamCaptor.toString();
    assertTrue(actualOutPut.contains(mock.getCities()));
  }

  @AfterEach
  public void tearDown() {
    DbConnection.closeDbConnection();
    System.setOut(standardOut);
  }
}