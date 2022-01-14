package com.nord.service.rewardPoints.interfaces;

/**
 * Interface that is implemented by Reward Points
 * @author Abdul Mueed Qadri
 */
public interface IRewardPoints {

  /**
   * Handles the IO from the user and based on the input shows reward points
   * to the user or withdraws the reward balance
   * @return true if the operation completes successfully
   */
  boolean rewardMenu();
}
