package com.nord.service.creditScoreLoan.interfaces;

import com.nord.persistence.creditScoreLoan.interfaces.ICreditScoreLoanModel;
import com.nord.view.creditScoreLoan.interfaces.ICreditScoreLoanView;

import java.util.Date;
import java.util.List;

public interface IPayCreditLoan {

  /**
   * This method uses unpaid userLoans to pay the loan of the user
   * @param userLoans
   */
  void payCreditLoanByGivenLoans(List<ICreditScoreLoanModel> userLoans  );

  /**
   * this method uses amount and date to pay the loan of the user
   * @param amount
   * @param date
   * @return true if successful
   */
  boolean payCreditLoanByDate(int amount, Date date);
  /**
   * this method counts the pay loan count from total list of loans
   * @param list
   * @return int count
   */
  int getPayLoanCount(List<ICreditScoreLoanModel> list);
  IPayCreditLoan setView(ICreditScoreLoanView view);
  IPayCreditLoan setFactory(ICreditScoreLoanFactory factory);
}
