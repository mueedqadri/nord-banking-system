package com.nord.persistence.rewardPoints;

import com.nord.persistence.rewardPoints.interfaces.IRewardPointModel;

import java.time.LocalDate;

/**
 * Reward point model class
 * @author Abdul Mueed Qadri
 */
public class RewardPointModel implements IRewardPointModel {
  private LocalDate rewardPointAddDate;
  private LocalDate rewardPointExpireDate;
  private long rewardPointBalance;
  private double dollarAmount;

  @Override
  public double getDollarAmount() {
    return dollarAmount;
  }

  @Override
  public void setDollarAmount(double dollarAmount) {
    this.dollarAmount = dollarAmount;
  }

  @Override
  public LocalDate getRewardPointAddDate() {
    return rewardPointAddDate;
  }

  @Override
  public void setRewardPointAddDate(LocalDate rewardPointAddDate) {
    this.rewardPointAddDate = rewardPointAddDate;
  }

  @Override
  public LocalDate getRewardPointExpireDate() {
    return rewardPointExpireDate;
  }

  @Override
  public void setRewardPointExpireDate(LocalDate rewardPointExpireDate) {
    this.rewardPointExpireDate = rewardPointExpireDate;
  }

  @Override
  public long getRewardPointBalance() {
    return rewardPointBalance;
  }

  @Override
  public void setRewardPointBalance(long rewardPointBalance) {
    this.rewardPointBalance = rewardPointBalance;
  }
}
