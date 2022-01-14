package com.nord.persistence.fixedDeposit;

import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlansModel;

/**
 * FD plan model
 * @author Abdul Mueed Qadri
 */
public class FixedDepositPlansModel implements IFixedDepositPlansModel {
    private int id;
    private int maxDuration;
    private int minDuration;
    private double interest;
    private String type;
    private double interestSeniors;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    @Override
    public void setMinDuration(int minDuration) {
        this.minDuration = minDuration;
    }

    @Override
    public void setInterest(double interest) {
        this.interest = interest;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setInterestSeniors(double interestSeniors) {
        this.interestSeniors = interestSeniors;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getMaxDuration() {
        return maxDuration;
    }

    @Override
    public int getMinDuration() {
        return minDuration;
    }

    @Override
    public double getInterest() {
        return interest;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public double getInterestSeniors() {
        return interestSeniors;
    }
}
