package com.nord.service.rewardPoints.mock;

import com.nord.persistence.rewardPoints.RewardPointModel;
import com.nord.persistence.rewardPoints.interfaces.IRewardPointModel;
import com.nord.common.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock Data for reward points
 * @author Abdul Mueed Qadri
 */

public class RewardPointsMockData {

  private int REWARD_BALANCE = 10000;
  private int EXPIRY_DAYS = 100;

  public List<IRewardPointModel>  getRewards(){
    List<IRewardPointModel> list = new ArrayList<>();
    IRewardPointModel reward = new RewardPointModel();
    reward.setDollarAmount(Utils.rewardToDollar(REWARD_BALANCE));
    reward.setRewardPointExpireDate(LocalDate.now().plusDays(EXPIRY_DAYS));
    reward.setRewardPointBalance(REWARD_BALANCE);
    reward.setRewardPointAddDate(LocalDate.now());
    list.add(reward);
    return list;
  }
}
