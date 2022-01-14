package com.nord.service.creditScoreLoan.interfaces;


import com.nord.view.creditScoreLoan.interfaces.ICreditScoreLoanView;

public interface IApplyForCreditLoan {
  /**
  *this method handles application process of all the user for applying credit loan
  *@param
  */
  void applyForAll();

  /**
  *this method handles application process for the users who are having decent credit score
  * @param
   */
  void applyForDecent();
  /**
  *this method handles application process for the users who are having exceptional credit score
   * @param
   */
  void applyForExceptional();
  IApplyForCreditLoan setView(ICreditScoreLoanView view);
  IApplyForCreditLoan setFactory(ICreditScoreLoanFactory factory);
}
