package com.nord.view.creditScoreLoan.interfaces;

import com.nord.persistence.creditScoreLoan.CreditScoreLoanModel;
import com.nord.persistence.creditScoreLoan.interfaces.ICreditScoreLoanModel;

import java.util.List;

public interface ICreditScoreLoanView {

  void showMenu();
  int getIntegerInput();
  void showInvalidInput();
  void noLoanBefore();
  void returnMenu();
  void returnMenu(int n);
  void showPreviousLoans(List<ICreditScoreLoanModel> userLoans);
  void noPendingLoan();
  void selectLoanLine();
  void payLoan(List<ICreditScoreLoanModel> unpaidLoans);
  void loanPaymentSuccess();
  void noBalance();
  void tipsForImprove();
  void poorCreditScore();
  void decentCreditScore();
  void exceptionalCreditScore();
  void enterAmount();
  void congratulationForLoanGranted(String strDate);
  void alreadyRunningLoan(int count);
}
