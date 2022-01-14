package com.nord.persistence.billing.interfaces;

import java.sql.Date;

public interface IBillingModel {
    int getUserId();

    String getPayeeName();

    String getAcctNumber();

    String getPaymentInfo();

    Double getAmount();

    Date getPaymentDate();

    String getPaymentCycle();

    void setUserId(int userId);

    void setPayeeName(String payeeName);

    void setAcctNumber(String acctNumber);

    void setPaymentInfo(String paymentInfo);

    void setAmount(double amount);

    void setPaymentDate(Date paymentDate);

    void setPaymentCycle(String paymentCycle);
}
