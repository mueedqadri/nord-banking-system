package com.nord.persistence.billing.interfaces;

public interface IAutomaticBillPayment {
    boolean deductMoney(int userId, double amount);
}
