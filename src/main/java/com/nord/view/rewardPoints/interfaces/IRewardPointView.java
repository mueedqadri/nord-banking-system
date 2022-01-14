package com.nord.view.rewardPoints.interfaces;

import com.nord.persistence.rewardPoints.interfaces.IRewardPointModel;
import com.nord.view.interfaces.IUserInterface;

import java.util.List;

/**
 * Interface for reward points view
 * @author Abdul Mueed Qadri
 */
public interface IRewardPointView extends IUserInterface {

  void showHeader();

  void showMenu();

  void showRewardPoints(List<IRewardPointModel> list);

  void showNoRewards();

  void successfulConversion(double dollarAmount);
}
