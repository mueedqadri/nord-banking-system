package com.nord.service.creditScoreLoan;

import com.nord.persistence.creditScoreLoan.CreditScore;
import com.nord.persistence.creditScoreLoan.CreditScoreLoanHistoryModel;
import com.nord.persistence.creditScoreLoan.CreditScoreLoanModel;
import com.nord.persistence.creditScoreLoan.CustomerBalanceManagement;
import com.nord.persistence.creditScoreLoan.interfaces.ICreditScore;
import com.nord.persistence.creditScoreLoan.interfaces.ICreditScoreLoanHistoryModel;
import com.nord.persistence.creditScoreLoan.interfaces.ICreditScoreLoanModel;
import com.nord.persistence.creditScoreLoan.interfaces.ICustomerBalanceManagement;
import com.nord.service.creditScoreLoan.interfaces.*;

/**
 * This is the factory class for CreditScore Loan feature, it returns new object of concrete objects
 * whenever developer needs to use databases apart from that It also provides feature classes access
 * with the new concrete object as return type
 * @author Harit Patwa
 */
public class CreditScoreLoanFactory implements ICreditScoreLoanFactory {

  @Override
  public ICreditScoreLoanHistory creditScoreLoanHistory() {
    return new CreditScoreLoanHistory();
  }

  @Override
  public IApplyForCreditLoan applyForCreditLoan() {
    return new ApplyForCreditLoan();
  }

  @Override
  public IPayCreditLoan payForCreditLoan() {
    return new PayCreditLoan();
  }

  @Override
  public ICreditScoreLoanHistoryModel getCreditScoreLoanHistoryModel() {
    return new CreditScoreLoanHistoryModel();
  }

  @Override
  public ICreditScore getCreditScoreModel() {
    return new CreditScore();
  }

  @Override
  public ICreditScoreLoanModel getCreditScoreLoanModel() {
    return new CreditScoreLoanModel();
  }

  @Override
  public ICustomerBalanceManagement getCustomerBalanceManagement() {
    return new CustomerBalanceManagement();
  }

}
