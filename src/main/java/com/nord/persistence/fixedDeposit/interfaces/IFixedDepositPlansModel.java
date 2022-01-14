package com.nord.persistence.fixedDeposit.interfaces;

/**
 * Interface implemented by Fd Plan Model
 * @author Abdul Mueed Qadri
 */
public interface IFixedDepositPlansModel {
    int getId();

    int getMaxDuration();

    int getMinDuration();

    double getInterest();

    String getType();

    double getInterestSeniors();

    void setId(int id);

    void setMaxDuration(int maxDuration);

    void setMinDuration(int minDuration);

    void setInterest(double interest);

    void setType(String type);

    void setInterestSeniors(double interestSeniors);
}
