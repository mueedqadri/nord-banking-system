package com.nord.view.fixedDeposit;

import com.nord.view.fixedDeposit.intrefaces.IFixedDepositMainView;

/**
 * Main menu view for fixed deposit
 * @author Abdul Mueed Qadri
 */
public class FixedDepositMainView extends AbstractFixedDepositView implements IFixedDepositMainView {

  @Override
  public void showMenu() {
    showHeader();
    System.out.println("1. View your Fixed Deposit");
    System.out.println("2. Open new short term Fixed Deposit");
    System.out.println("3. Open new medium term Fixed Deposit");
    System.out.println("4. Open new long term Fixed Deposit");
    System.out.println("5. Fixed Deposit Estimator");
    System.out.println("6. Back to main menu");
  }
}
