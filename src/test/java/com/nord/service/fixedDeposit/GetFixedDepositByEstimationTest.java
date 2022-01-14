package com.nord.service.fixedDeposit;

import com.nord.persistence.DbConnection;
import com.nord.persistence.fixedDeposit.UserFixedDepositModel;
import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlanPersistence;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositsPersistence;
import com.nord.service.Context;
import com.nord.service.fixedDeposit.interfaces.IGetFixedDeposit;
import com.nord.service.fixedDeposit.mock.MockFixedDeposit;
import com.nord.service.fixedDeposit.mock.MockFixedDepositPlanPersistence;
import com.nord.service.fixedDeposit.mock.MockUserFdPersistence;
import com.nord.view.fixedDeposit.FixedDepositPlanView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test getting fixed deposit by estimation
 * @author Abdul Mueed Qadri
 */
public class GetFixedDepositByEstimationTest {
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private final int TEST_USER_ID = 1;
  private MockFixedDeposit mock;
  private IFixedDepositPlanPersistence plans;
  private IUserFixedDepositsPersistence userFd;
  private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  @BeforeEach
  public void setUp() {
    mock = new MockFixedDeposit();
    Context.setLoggedInUserId(TEST_USER_ID);
    System.setOut(new PrintStream(outputStreamCaptor));
    userFd = new MockUserFdPersistence(mock.getMockFd());
    plans = new MockFixedDepositPlanPersistence();
  }

  @Test
  void getFdByEstimationTest() {
    String input = "1000 12/08/2021" ;
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    IGetFixedDeposit fixedDeposits =new GetFixedDepositByEstimation()
            .setView(new FixedDepositPlanView())
            .setSelectedFd(new UserFixedDepositModel());
    IUserFixedDepositModel selectedFd =
            fixedDeposits.getFixedDeposit(plans.getFds());
    assertEquals(selectedFd.getAmount(), 1000.0);
    assertEquals(selectedFd.getPlanId(), 10);
    assertEquals(selectedFd.getWithdrawalDate(), LocalDate.parse("12/08/2021", dtf));
    assertEquals(selectedFd.getAmount(), 1000.0);
    assertEquals(selectedFd.getInterest(), 2.9);
    assertEquals(selectedFd.getIsUpgraded(), false);
  }

  @AfterEach
  public void tearDown() {
    DbConnection.closeDbConnection();
    System.setOut(standardOut);
  }
}
