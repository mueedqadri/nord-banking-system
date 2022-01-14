package com.nord.persistence.loan;

import com.nord.persistence.loan.interfaces.ILoanModel;
import com.nord.service.Context;

import java.sql.Date;

/**
 * This class implements the loan model that contains the attributes of a loan
 * @author Samir Anwar Rana
 */

public class LoanModel implements ILoanModel {

    private int userId;
    private String loanType;
    private double amount;
    private Date appliedDate;
    private Date repaymentDate;
    private int interestRate;
    private boolean status;

    public LoanModel() {
        this.userId = Context.getLoggedInUserId();
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public String getLoanType() {
        return loanType;
    }

    @Override
    public Double getAmount() {
        return amount;
    }

    @Override
    public Date getAppliedDate() {
        return appliedDate;
    }

    @Override
    public Date getRepaymentDate() {
        return repaymentDate;
    }

    @Override
    public int getInterestRate() {
        return interestRate;
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }

    @Override
    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    @Override
    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }
}

