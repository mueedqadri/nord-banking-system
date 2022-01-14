package com.nord.service.billing.interfaces;

import java.util.Map;

/**
 * This interface consist of the home page for the billing feature and the handlers that are used to perform
 * billing operations
 * @author Samir Anwar Rana
 */

public interface IBillingService {
    void billingHomePage();

    boolean handleSuccessfulBillPaymentCreation(Map<String, Object> billDetails);

    boolean handleDeletionOfBill(String payee_name);
}
