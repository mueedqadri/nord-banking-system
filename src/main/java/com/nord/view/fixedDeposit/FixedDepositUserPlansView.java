package com.nord.view.fixedDeposit;

import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositUserPlansView;

import java.util.List;

/**
 * View to display fixed deposit User plans
 * @author Abdul Mueed Qadri
 */
public class FixedDepositUserPlansView extends AbstractFixedDepositView implements IFixedDepositUserPlansView {

  @Override
  public void showAllUserFds(List<IUserFixedDepositModel> userFixedDepositModels) {
    showHeader();
    int count = 1;
    System.out.println("Select a fixed deposit:");
    for (IUserFixedDepositModel fd : userFixedDepositModels) {
      System.out.println(count++ + ". From " + fd.getStartDate().format(dtf) + " to "
              + fd.getWithdrawalDate().format(dtf));
    }
    System.out.println(count + ". Go to previous menu");
  }

  @Override
  public void showUserFdDetails(IUserFixedDepositModel selectedFd) {
    showHeader();
    System.out.println("Fixed Deposit Summary");
    showFdSummary(selectedFd);
    System.out.println("0. Go back to Fixed Deposit Main menu");
    System.out.println("1. Close Fixed Deposit");
  }

  @Override
  public void showSuccessfulWithdrawal(double amount){
    System.out.println("$"+amount+" has been credited to your account");
    System.out.println("0: Go to previous menu");
  }

  @Override
  public void showImmatureWithdrawalWarning(){
    showHeader();
    System.out.println("WARNING! ");
    System.out.println("Are you sure that you want to withdraw the balance " +
            "before the due date");
    System.out.println("0. Go to previous menu");
    System.out.println("1. Confirm withdrawal");
  }
}
