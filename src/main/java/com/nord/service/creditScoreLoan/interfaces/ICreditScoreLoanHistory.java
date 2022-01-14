package com.nord.service.creditScoreLoan.interfaces;

import com.nord.persistence.creditScoreLoan.interfaces.ICreditScoreLoanModel;
import com.nord.view.creditScoreLoan.interfaces.ICreditScoreLoanView;

import java.util.List;

public interface ICreditScoreLoanHistory {
  /**
  *Shows the List object of user's previous loan history
  *@param
   */
  void showLoanHistory(List<ICreditScoreLoanModel> userLoans);
  ICreditScoreLoanHistory setView(ICreditScoreLoanView view);
  ICreditScoreLoanHistory setFactory(ICreditScoreLoanFactory factory);
}
