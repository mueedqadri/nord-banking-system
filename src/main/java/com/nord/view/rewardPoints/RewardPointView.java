package com.nord.view.rewardPoints;

import com.nord.persistence.rewardPoints.interfaces.IRewardPointModel;
import com.nord.view.AbstractUserInterface;
import com.nord.view.rewardPoints.interfaces.IRewardPointView;

import java.util.List;

/**
 * View to handle IO for Reward Points
 * @author Abdul Mueed Qadri
 */
public class RewardPointView extends AbstractUserInterface implements IRewardPointView {
  @Override
  public void showHeader(){
    System.out.println("=============================================================");
    System.out.println("                   Reward Points                     ");
    System.out.println("=============================================================");
  }

  @Override
  public void showMenu() {
    showHeader();
    System.out.println("1. View your reward point balance");
    System.out.println("2. Convert to reward points to Dollars");
    System.out.println("3. Back to main menu");
  }

  @Override
  public void showRewardPoints(List<IRewardPointModel> list){
    showHeader();
    System.out.println(String.format("%10s %10s %20s %20s", "Points",
            "Dollars", "Add Date", "Expiry Date"));
    for(IRewardPointModel item: list){
      System.out.println(String.format("%10s %10s %20s %20s",
              item.getRewardPointBalance(),
              item.getDollarAmount(), item.getRewardPointAddDate(), item.getRewardPointExpireDate()));
    }
    System.out.println("0. Back to previous menu");
  }

  @Override
  public void showNoRewards(){
    showHeader();
    System.out.println("You dont have any available rewards");
    System.out.println("0. Back to previous menu");
  }

  @Override
  public void successfulConversion(double dollarAmount){
    showHeader();
    System.out.println("Congrats! $"+dollarAmount+" have been added to your " +
            "bank balance");
    System.out.println("0. Back to previous menu");
  }
}
