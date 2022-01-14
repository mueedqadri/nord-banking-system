package com.nord.persistence.fixedDeposit.interfaces;

import java.time.LocalDate;

/**
 * Interface for User FD model
 * @author Abdul Mueed Qadri
 */
public interface IUserFixedDepositModel {

    boolean getIsUpgraded();

    void setIsUpgraded(boolean upgraded);

    int getPlanId();

    void setPlanId(int planId);

    int getFdId();

    void setFdId(int fdId);

    LocalDate getWithdrawalDate();

    void setWithdrawalDate(LocalDate withdrawalDate);

    LocalDate getStartDate();

    void setStartDate(LocalDate startDate);

    double getAmount();

    void setAmount(double amount);

    double getInterest();

    void setInterest(double interest);

    double getProfit();

    void setProfit(double profit);
}
