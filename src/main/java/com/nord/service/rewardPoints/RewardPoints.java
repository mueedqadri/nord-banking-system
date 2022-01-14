package com.nord.service.rewardPoints;

import com.nord.common.Constants;
import com.nord.persistence.rewardPoints.interfaces.IRewardPointModel;
import com.nord.persistence.rewardPoints.interfaces.IRewardPointsDb;
import com.nord.service.rewardPoints.interfaces.IRewardPoints;
import com.nord.common.Utils;
import com.nord.view.rewardPoints.interfaces.IRewardPointView;

import java.util.List;

/**
 * Handles the RewardPoints ie, displaying users their valid reward points
 * @author Abdul Mueed Qadri
 */
public class RewardPoints implements IRewardPoints {

  private IRewardPointView view;
  private IRewardPointsDb rewardPointsDb;
  private List<IRewardPointModel> rewardModelList;

  public RewardPoints setRewardPointsDb(IRewardPointsDb rewardPointsDb){
    this.rewardPointsDb = rewardPointsDb;
    return this;
  };

  public RewardPoints setRewardPointsView(IRewardPointView view){
    this.view = view;
    return this;
  };

  private void getPoints(){
    if(rewardModelList.isEmpty()){
      view.showNoRewards();
    }else {
      view.showRewardPoints(rewardModelList);
    }
    while(true){
      int input = view.getIntegerInput();
      if (input == 0) {
        return;
      } else {
        view.showInvalidInputNumber();
      }
    }
  }

  private long addAllRewardPoints(){
    long sum = 0;
    for(IRewardPointModel item: rewardModelList){
      sum = sum + item.getRewardPointBalance();
    }
    return sum;
  }

  private void convertToDollars(){
    if(rewardModelList.isEmpty()){
      view.showNoRewards();
    }else {
      double dollarAmount = Utils.rewardToDollar(addAllRewardPoints());
      rewardPointsDb.removeAllPoints();
      rewardPointsDb.makeNewTransaction(dollarAmount, "+", Constants.REWARD_POINTS);
      view.successfulConversion(dollarAmount);
    }
    while (true){
      int input = view.getIntegerInput();
      if (input == 0) {
        return;
      } else {
        view.showInvalidInputNumber();
      }
    }
  }

  @Override
  public boolean rewardMenu(){
    while(true){
      rewardModelList = rewardPointsDb.getRewardPoints();
      view.showMenu();
      int input = view.getIntegerInput();
      switch (input){
        case 1:
          getPoints();
          break;
        case 2:
          convertToDollars();
          break;
        case 3:
          return true;
        default:
          view.showInvalidInputNumber();
      }
    }
  }
}
