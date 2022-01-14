package com.nord.view.fixedDeposit;

import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositConfirmationView;

/**
 * View for fixedDepositConfirmation
 * @author Abdul Mueed Qadri
 */
public class FixedDepositConfirmationView extends AbstractFixedDepositView implements IFixedDepositConfirmationView {

  @Override
  public void showFdConfirmation(IUserFixedDepositModel fd) {
    System.out.println("You have selected following plan:");
    showFdSummary(fd);
    System.out.println("1. To confirm the plan");
    System.out.println("2. Return to Previous page");
  }

  @Override
  public void showSuggestion(String suggestion) {
    System.out.println(suggestion);
    System.out.println("3. Upgrade your plan");
  }


  @Override
  public void showFdAddedSuccessful() {
    System.out.println("FD added successfully ");
    System.out.println("0. Return to main Fd menu");
    do {
      int input = getIntegerInput();
      if (input == 0) {
        return;
      } else {
        showInvalidInputNumber();
      }
    } while (true);
  }
}
