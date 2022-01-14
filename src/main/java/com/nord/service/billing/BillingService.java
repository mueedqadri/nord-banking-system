package com.nord.service.billing;

import com.nord.common.Constants;
import com.nord.persistence.billing.Billing;
import com.nord.persistence.billing.interfaces.IBilling;
import com.nord.persistence.billing.interfaces.IBillingModel;
import com.nord.service.billing.interfaces.IBillingService;
import com.nord.service.loan.LoanService;
import com.nord.service.loan.interfaces.ILoanService;
import com.nord.view.bill.BillingView;
import com.nord.view.bill.interfaces.IBillingView;
import com.nord.view.loan.interfaces.ILoanView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class is responsible for performing the functions of the billing feature
 * @author Samir Anwar Rana
 */

public class BillingService implements IBillingService {

    private IBillingView view = new BillingView();
    private IBilling model = new Billing();
    private IBillingService factory;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
    private List<IBillingModel> billingModels;

    public BillingService setView(IBillingView view) {
        this.view = view;
        return this;
    }

    public BillingService setFactory(IBillingService factory) {
        this.factory = factory;
        return this;
    }

    public BillingService(){
    }

    @Override
    public void billingHomePage(){
        billingModels =  model.retrieveAllBills();
        automaticBillPaymentJob();
        view.showMenu();
        while (true) {
            int input = view.getIntegerInput();
            switch (input) {
                case 1:
                    addBillPayment();
                    return;
                case 2:
                    deleteBillPayment();
                    return;
                case 3:
                    return;
            }
        }
    }

    private void addBillPayment(){
        int input;
        Map<String, Object> billDetails = new LinkedHashMap<String, Object>(){{
            put(Constants.PAYEE_NAME, null);
            put(Constants.ACCOUNT_NUMBER, null);
            put(Constants.PAYMENT_INFO, null);
            put(Constants.AMOUNT, null);
            put(Constants.PAYMENT_DATE, null);
            put(Constants.PAYMENT_CYCLE, null);
        }};
        while(true) {
            billDetails = view.addNewBilling(billDetails);
            input = view.getIntegerInput();
            if(input == 1){
                handleSuccessfulBillPaymentCreation(billDetails);
                return;
            } else if(input == 2) {
                return;
            } else {
                view.showInvalidInput();
            }
        }
    }

    @Override
    public boolean handleSuccessfulBillPaymentCreation(Map<String, Object> billDetails){
        if(model.addBillPayment(billDetails)){
            view.accountAddedSuccessfully();
            return true;
        }
        return false;
    }

    private void deleteBillPayment(){
        view.showAllBilling(model.retrieveBillsForUser());
        String input = view.getInput();
        if(!input.equals("0")){
            handleDeletionOfBill(input);
            view.billDeletedSuccessfully();
        }
    }

    @Override
    public boolean handleDeletionOfBill(String payee_name){
        model.deleteBill(payee_name);
        return true;
    }

    public void automaticBillPaymentJob() {
        for(IBillingModel bm: billingModels){
            String paymentCycle = bm.getPaymentCycle();
            double amount = bm.getAmount();
            switch (paymentCycle) {
                case Constants.DAILY:
                    scheduler.scheduleAtFixedRate(new AutomaticBillPaymentService(amount), 0, 1, TimeUnit.DAYS);
                    break;
                case Constants.WEEKLY:
                    scheduler.scheduleAtFixedRate(new AutomaticBillPaymentService(amount), 0, 7, TimeUnit.DAYS);
                    break;
                case Constants.MONTHLY:
                    scheduler.scheduleAtFixedRate(new AutomaticBillPaymentService(amount), 0, 30, TimeUnit.DAYS);
                    break;
                case Constants.YEAR:
                    scheduler.scheduleAtFixedRate(new AutomaticBillPaymentService(amount), 0, 365, TimeUnit.DAYS);
                    break;
            }
        }
    }

}
