package com.nord.persistence.billing;

import com.nord.persistence.billing.interfaces.IBillingModel;
import com.nord.service.Context;

import java.sql.*;

/**
 * This class implements the billing model that contains the attributes of a bill
 * @author Samir Anwar Rana
 */

public class BillingModel implements IBillingModel {

    private int userId;
    private String payeeName;
    private String acctNumber;
    private String paymentInfo;
    private double amount;
    private Date paymentDate;
    private String paymentCycle;

    public BillingModel() {
        this.userId = Context.getLoggedInUserId();
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public String getPayeeName() {
        return payeeName;
    }

    @Override
    public String getAcctNumber() {
        return acctNumber;
    }

    @Override
    public String getPaymentInfo() {
        return paymentInfo;
    }

    @Override
    public Double getAmount() {
        return amount;
    }

    @Override
    public Date getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String getPaymentCycle() {
        return paymentCycle;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    @Override
    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
    }

    @Override
    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public void setPaymentCycle(String paymentCycle) {
        this.paymentCycle = paymentCycle;
    }
}






