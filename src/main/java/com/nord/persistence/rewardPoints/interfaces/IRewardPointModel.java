package com.nord.persistence.rewardPoints.interfaces;

import java.time.LocalDate;

/**
 * Interface implemented by RewardPointModel
 * @author Abdul Mueed Qadri
 */
public interface IRewardPointModel {

  double getDollarAmount();

  void setDollarAmount(double dollarAmount);

  LocalDate getRewardPointAddDate();

  void setRewardPointAddDate(LocalDate rewardPointAddDate);

  LocalDate getRewardPointExpireDate();

  void setRewardPointExpireDate(LocalDate rewardPointExpireDate);

  long getRewardPointBalance();

  void setRewardPointBalance(long rewardPointBalance);
}
