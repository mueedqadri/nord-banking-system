package com.nord.persistence.rewardPoints.interfaces;

import com.nord.persistence.bankingTransactions.interfaces.IBanking;

import java.util.List;

/**
 * Interface for Reward Point persistence
 * @author Abdul Mueed Qadri
 */
public interface IRewardPointsDb extends IBanking {

  /**
   * Fetches the list of reward points from the database
   * @return list of RewardPoints
   */
  List<IRewardPointModel> getRewardPoints();

  /**
   * When the user converts the rewards to dollars all points are withdrawn
   * and the equivalent dollar amount is added to the balance
   */
  void removeAllPoints();
}
