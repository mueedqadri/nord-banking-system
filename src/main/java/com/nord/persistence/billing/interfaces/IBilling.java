package com.nord.persistence.billing.interfaces;

import com.nord.service.creditCardServicesAndRecommendation.ApplyForCreditCard;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.IApplyForCreditCard;

import java.util.List;
import java.util.Map;

public interface IBilling {
    boolean addBillPayment(Map<String, Object> billDetails);

    List<IBillingModel> retrieveBillsForUser();

    boolean deleteBill(String payeeName);

    List<IBillingModel> retrieveAllBills();
}
