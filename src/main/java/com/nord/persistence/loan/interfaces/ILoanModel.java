package com.nord.persistence.loan.interfaces;

import java.sql.Date;

public interface ILoanModel {
    int getUserId();

    String getLoanType();

    Double getAmount();

    Date getAppliedDate();

    Date getRepaymentDate();

    int getInterestRate();

    boolean getStatus();

    void setUserId(int userId);

    void setLoanType(String loanType);

    void setAmount(double amount);

    void setAppliedDate(Date appliedDate);

    void setRepaymentDate(Date repaymentDate);

    void setInterestRate(int interestRate);

    void setStatus(boolean status);
}
