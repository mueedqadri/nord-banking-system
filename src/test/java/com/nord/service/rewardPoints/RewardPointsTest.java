
package com.nord.service.rewardPoints;

import com.nord.persistence.DbConnection;
import com.nord.persistence.rewardPoints.interfaces.IRewardPointsDb;
import com.nord.service.rewardPoints.interfaces.IRewardPoints;
import com.nord.service.rewardPoints.mock.RewardPointsMockData;
import com.nord.service.rewardPoints.mock.RewardPointsMockPersistence;
import com.nord.view.rewardPoints.RewardPointView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Test reward points fetching and withdrawal
 * @author Abdul Mueed Qadri
 */
class RewardPointsTest {

  private int BALANCE_FOR_TEST_USER = 1000;
  private final PrintStream standardOut = System.out;
  private RewardPointsMockData mock;
  private IRewardPointsDb persistence;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    mock = new RewardPointsMockData();
    persistence = new RewardPointsMockPersistence(BALANCE_FOR_TEST_USER,
          mock.getRewards());
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  public void getRewardsTest(){
    String input = "1 0 3";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    IRewardPoints rewardPoints =  new RewardPoints()
            .setRewardPointsDb(persistence)
            .setRewardPointsView(new RewardPointView());
    assertTrue(rewardPoints.rewardMenu());
  }

  @Test
  public void withdrawRewardsTest(){
    String input = "2 0 3";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    double initialBalance = persistence.getCustomerBalance();
    IRewardPoints rewardPoints =  new RewardPoints()
            .setRewardPointsDb(persistence)
            .setRewardPointsView(new RewardPointView());
    rewardPoints.rewardMenu();
    double updatedBalance = persistence.getCustomerBalance();
    double rewardDollars = mock.getRewards().get(0).getDollarAmount();
    assertEquals(initialBalance + rewardDollars, updatedBalance, 2);
  }


  @AfterEach
  public void tearDown() {
    DbConnection.closeDbConnection();
    System.setOut(standardOut);
  }
}