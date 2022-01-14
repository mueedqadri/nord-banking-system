package com.nord.service.creditScoreLoan;

import com.nord.persistence.creditScoreLoan.interfaces.ICreditScoreLoanModel;
import com.nord.service.Context;
import com.nord.service.creditScoreLoan.interfaces.ICreditScoreLoanService;
import com.nord.service.creditScoreLoan.interfaces.ICreditScoreLoanFactory;
import com.nord.view.creditScoreLoan.interfaces.ICreditScoreLoanView;

import java.util.List;

/**
 * This class represents Credit Score Loan services, It handles navigation of the feature
 * and access to other tasks from the main service class
 * @author Harit Patwa
 */
public class CreditScoreLoanService implements ICreditScoreLoanService {

  private int userID;
  private ICreditScoreLoanView view;
  private List<ICreditScoreLoanModel> userLoans;
  private ICreditScoreLoanFactory factory;
  public CreditScoreLoanService(ICreditScoreLoanFactory creditScoreLoanFactory) {
    this.userID = Context.getLoggedInUserId();
    this.factory = creditScoreLoanFactory;
    userLoans = factory.getCreditScoreLoanHistoryModel().getCreditScoreLoanHistory();
  }
  public CreditScoreLoanService()
  {

  }
  public ICreditScoreLoanService setFactory(ICreditScoreLoanFactory factory) {
    this.factory = factory;
    userLoans = this.factory.getCreditScoreLoanHistoryModel().getCreditScoreLoanHistory();
    return this;
  }
  @Override
  public ICreditScoreLoanService setView(ICreditScoreLoanView view) {
    this.view=view;
    return this;
  }
  @Override
  public void creditScoreLoanHomePage() {
    while (true) {
      view.showMenu();
      int input = view.getIntegerInput();
      switch (input) {
        case 1:
          factory.creditScoreLoanHistory()
                  .setView(view)
                  .setFactory(factory)
                  .showLoanHistory(userLoans);
          break;
        case 2:
          factory.applyForCreditLoan()
                  .setView(view)
                  .setFactory(factory)
                  .applyForAll();
          updateCreditLoanService();;
          break;
        case 3:
          factory.payForCreditLoan()
                  .setView(view)
                  .setFactory(factory)
                  .payCreditLoanByGivenLoans(userLoans);
          updateCreditLoanService();
          break;
        case 4:
          showCreditScoreLoanService();
          break;
        case 5:
          return;
        default:
          view.showInvalidInput();
          break;
      }
    }
  }

  @Override
  public void showCreditScoreLoanService() {
    view.tipsForImprove();
    view.returnMenu();
    int input = view.getIntegerInput();
    while (input != 1) {
      view.showInvalidInput();
      view.returnMenu();
      input = view.getIntegerInput();
    }
  }

  public boolean updateCreditLoanService()
  {
    this.userLoans =this.factory
            .getCreditScoreLoanHistoryModel()
            .getCreditScoreLoanHistory();
    return true;
  }
}
