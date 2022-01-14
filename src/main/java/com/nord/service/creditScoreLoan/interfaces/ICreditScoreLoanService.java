package com.nord.service.creditScoreLoan.interfaces;

import com.nord.view.creditScoreLoan.interfaces.ICreditScoreLoanView;

public interface ICreditScoreLoanService {

  /**
   * Shows the main home page of Credit Score Loan feature, handles
   * business logic for the navigation of the pages
   * @param
   */
   void creditScoreLoanHomePage();

  ICreditScoreLoanService setView(ICreditScoreLoanView view);
  /**
   * shows the tips for the user to improve their credit score
   * @param
   */
  void showCreditScoreLoanService();
  /**
   * updates the user loans and services for the user
   * @return true if success
   */
  boolean updateCreditLoanService();
}
