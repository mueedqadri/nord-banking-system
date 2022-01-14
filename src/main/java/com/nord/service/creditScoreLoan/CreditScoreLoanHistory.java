package com.nord.service.creditScoreLoan;

import com.nord.persistence.creditScoreLoan.interfaces.ICreditScoreLoanModel;
import com.nord.service.creditScoreLoan.interfaces.ICreditScoreLoanFactory;
import com.nord.service.creditScoreLoan.interfaces.ICreditScoreLoanHistory;
import com.nord.view.creditScoreLoan.interfaces.ICreditScoreLoanView;

import java.util.List;

/**
 * This class shows credit score loan history of the user
 * @author Harit Patwa
 */
public class CreditScoreLoanHistory implements ICreditScoreLoanHistory {
  private ICreditScoreLoanView view;
  private ICreditScoreLoanFactory factory;

  public void showLoanHistory(List<ICreditScoreLoanModel> userLoans) {
    if(userLoans.isEmpty()) {
      view.noLoanBefore();
      view.returnMenu();
    } else {
      view.showPreviousLoans(userLoans);
    }
    int input = view.getIntegerInput();
    if(input != 1) {
      while (input != 1) {
        view.showInvalidInput();
        view.returnMenu();
        input = view.getIntegerInput();
      }
    }
  }

  @Override
  public ICreditScoreLoanHistory setView(ICreditScoreLoanView view) {
    this.view = view;
    return this;
  }
  @Override
  public ICreditScoreLoanHistory setFactory(ICreditScoreLoanFactory factory) {
    this.factory = factory;
    return this;
  }
}
