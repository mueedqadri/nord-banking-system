package com.nord.service.rewardPoints.mock;

import com.nord.persistence.rewardPoints.interfaces.IRewardPointModel;
import com.nord.persistence.rewardPoints.interfaces.IRewardPointsDb;

import java.util.ArrayList;
import java.util.List;


/**
 * Mock the persistence for reward points
 * @author Abdul Mueed Qadri
 */

public class RewardPointsMockPersistence implements IRewardPointsDb {

  private double customerBalance;
  private List<IRewardPointModel> rewardPointModelList;

  public RewardPointsMockPersistence(double customerBalance, List<IRewardPointModel> rewards){
    rewardPointModelList = rewards;
  }

  @Override
  public boolean makeNewTransaction(double transferAmount, String operator, String comment) {
    customerBalance = operator.equals("+")? customerBalance + transferAmount:
            customerBalance - transferAmount;
    return false;
  }

  @Override
  public double getCustomerBalance() {
    return customerBalance;
  }

  @Override
  public List<IRewardPointModel> getRewardPoints() {
    return rewardPointModelList;
  }

  @Override
  public void removeAllPoints() {
    rewardPointModelList = new ArrayList<>();
  }
}
