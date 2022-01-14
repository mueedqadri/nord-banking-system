package com.nord.service.fixedDeposit;

import com.nord.persistence.DbConnection;
import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlanPersistence;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositsPersistence;
import com.nord.service.Context;
import com.nord.service.fixedDeposit.interfaces.IFixedDeposit;
import com.nord.service.fixedDeposit.interfaces.IFixedDepositFactory;
import com.nord.service.fixedDeposit.mock.MockFixedDeposit;
import com.nord.service.fixedDeposit.mock.MockFixedDepositPlanPersistence;
import com.nord.service.fixedDeposit.mock.MockUserFdPersistence;
import com.nord.view.fixedDeposit.FixedDepositMainView;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositMainView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.format.DateTimeFormatter;

/**
 * Test creation of Fixed Deposits
 * @author  Abdul Mueed Qadri 
 */
public class CreateFixedDepositTest {
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private final int TEST_USER_ID = 1;
  private IFixedDepositPlanPersistence plans;
  private IUserFixedDepositsPersistence userFd;
  private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private MockFixedDeposit mock;

  @BeforeEach
  public void setUp() {
    mock = new MockFixedDeposit();
    Context.setLoggedInUserId(TEST_USER_ID);
    System.setOut(new PrintStream(outputStreamCaptor));
    userFd = new MockUserFdPersistence(mock.getMockFd());
    plans = new MockFixedDepositPlanPersistence();
  }

  @Test
  void fdAdditionTest(){
    String input = "1 0";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    IFixedDepositFactory fdFactory = new FixedDepositFactory();
    IFixedDepositMainView fdView = new FixedDepositMainView();
    IFixedDeposit fixed = new FixedDeposit()
            .setFactory(fdFactory)
            .setView(fdView)
            .setPersistence(plans);
  }

  @AfterEach
  public void tearDown() {
    DbConnection.closeDbConnection();
    System.setOut(standardOut);
  }
}
