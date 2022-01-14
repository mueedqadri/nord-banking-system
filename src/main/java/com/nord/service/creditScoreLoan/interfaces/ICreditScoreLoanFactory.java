package com.nord.service.creditScoreLoan.interfaces;

import com.nord.persistence.creditScoreLoan.interfaces.ICreditScore;
import com.nord.persistence.creditScoreLoan.interfaces.ICreditScoreLoanHistoryModel;
import com.nord.persistence.creditScoreLoan.interfaces.ICreditScoreLoanModel;
import com.nord.persistence.creditScoreLoan.interfaces.ICustomerBalanceManagement;

public interface ICreditScoreLoanFactory {

  ICreditScoreLoanHistory creditScoreLoanHistory();

  IApplyForCreditLoan applyForCreditLoan();

  IPayCreditLoan payForCreditLoan();

  ICreditScoreLoanHistoryModel getCreditScoreLoanHistoryModel();

  ICreditScore getCreditScoreModel();

  ICreditScoreLoanModel getCreditScoreLoanModel();

  ICustomerBalanceManagement getCustomerBalanceManagement();
}
