package com.nord.persistence.fixedDeposit;

import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;

import java.time.LocalDate;

/**
 * User FD Model
 * @author Abdul Mueed Qadri
 */
public class UserFixedDepositModel implements IUserFixedDepositModel {
    private int planId;
    private int fdId;
    private LocalDate withdrawalDate;
    private LocalDate startDate;
    private double amount;
    private double interest;
    private double profit;
    private boolean isUpgraded;

    @Override
    public boolean getIsUpgraded() {
        return isUpgraded;
    }

    @Override
    public void setIsUpgraded(boolean upgraded) {
        isUpgraded = upgraded;
    }

    @Override
    public int getPlanId() {
        return planId;
    }

    @Override
    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @Override
    public int getFdId() {
        return fdId;
    }

    @Override
    public void setFdId(int fdId) {
        this.fdId = fdId;
    }

    @Override
    public LocalDate getWithdrawalDate() {
        return withdrawalDate;
    }

    @Override
    public void setWithdrawalDate(LocalDate withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public double getInterest() {
        return interest;
    }

    @Override
    public void setInterest(double interest) {
        this.interest = interest;
    }

    @Override
    public double getProfit() {
        return profit;
    }

    @Override
    public void setProfit(double profit) {
        this.profit = profit;
    }
}
