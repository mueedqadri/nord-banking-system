package com.nord.view.fixedDeposit;

import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlansModel;
import com.nord.common.Utils;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositPlanView;

import java.util.List;

/**
 * View to display fixed deposit plans
 * @author Abdul Mueed Qadri
 */
public class FixedDepositPlanView extends AbstractFixedDepositView implements IFixedDepositPlanView {

  @Override
  public void showNoFdFound(){
    System.out.println("***  No Plan found in the selected duration  ***");
    System.out.println("             Please try again:\n");
  }

  @Override
  public void showFixedDepositPlanList(List<IFixedDepositPlansModel> fixedDepositPlans) {
    int count = 1;
    showHeader();
    System.out.println("Please select your preferred plan");
    for (IFixedDepositPlansModel fd : fixedDepositPlans) {
      System.out.println(count++ + ". " + Utils.daysConvertToString(fd.getMinDuration())
              + "to " + Utils.daysConvertToString(fd.getMaxDuration()) + " with interest rate " + fd.getInterest() + "%");
    }
    System.out.println(count + ". Return to previous page");
  }
}
