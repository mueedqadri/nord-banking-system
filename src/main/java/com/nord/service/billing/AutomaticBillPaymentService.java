package com.nord.service.billing;

import com.nord.persistence.billing.AutomaticBillPayment;
import com.nord.persistence.billing.Billing;
import com.nord.persistence.billing.interfaces.IAutomaticBillPayment;
import com.nord.service.Context;

/**
 * This class implements a runnable that runs when a job is scheduled to be executed
 * It is used to perform automatic deduction of bills
 * @author Samir Anwar Rana
 */

public class AutomaticBillPaymentService implements Runnable {

    private int userId;
    private double payAmount;
    private IAutomaticBillPayment model = new AutomaticBillPayment();


    public AutomaticBillPaymentService(double amount){
       userId = Context.getLoggedInUserId();
       payAmount = amount;

    }

    @Override
    public void run() {
        model.deductMoney(userId, payAmount);
        System.out.println("\nNOTIFICATION : " + payAmount + " has been deducted from your account");
    }

}
