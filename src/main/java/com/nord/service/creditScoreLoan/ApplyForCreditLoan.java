package com.nord.service.creditScoreLoan;

import com.nord.common.Constants;
import com.nord.service.creditScoreLoan.interfaces.IApplyForCreditLoan;
import com.nord.service.creditScoreLoan.interfaces.ICreditScoreLoanFactory;
import com.nord.view.creditScoreLoan.interfaces.ICreditScoreLoanView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class provides feature to apply for the credit score loan to all the uers
 * @author Harit Patwa
 */
public class ApplyForCreditLoan implements IApplyForCreditLoan {
    private int crsScore;
    private ICreditScoreLoanView view;
    private ICreditScoreLoanFactory factory;

    @Override
    public void applyForAll()
    {
        if(crsScore ==Constants.DEFAULT_CRS)
        {
             factory.getCreditScoreModel().insertCrsScore();
            crsScore =Constants.STANDARD_CRS;
        }
        if(crsScore <Constants.POOR_CRS)
        {
            view.poorCreditScore();
            view.returnMenu();
            int input = view.getIntegerInput();
            while(true)
            {
                if(input==1) {
                    return;
                } else {
                    view.showInvalidInput();
                    view.returnMenu();
                }
            }
        }else if(crsScore <Constants.DECENT_CRS) {
            view.decentCreditScore();
            applyForDecent();
        }
        else
        {
            view.exceptionalCreditScore();
            applyForExceptional();
        }

        view.returnMenu();
        int input = view.getIntegerInput();
        while(input!=1)
        {
            view.showInvalidInput();
            view.returnMenu();
            input=view.getIntegerInput();
        }
    }

    public void applyForDecent()
    {
        int count = factory.getCreditScoreLoanModel().getCreditScoreLoanCount();
        if(count ==0)
        {
            apply();
        }
        else
        {
            view.alreadyRunningLoan(count);
        }
    }

    private void apply() {
        view.enterAmount();
        int amount = view.getIntegerInput();
        while(amount > Constants.CREDIT_LOAN_MIN_AMOUNT)
        {
            view.showInvalidInput();
            view.enterAmount();
            amount=view.getIntegerInput();
        }
        if(amount <= Constants.CREDIT_LOAN_MIN_AMOUNT)
        {
            Calendar calendar= Calendar.getInstance();
            calendar.add(Calendar.DATE, 30);
            Date date=calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
            String strDate= formatter.format(date);
            factory.getCreditScoreLoanModel().applyForCreditScoreLoan(amount,strDate);
            int customerBalance = factory.getCustomerBalanceManagement().getCustomerBalance();
            factory.getCustomerBalanceManagement().setCustomerBalance(customerBalance+amount);
            view.congratulationForLoanGranted(strDate);
        }
    }

    public void applyForExceptional()
    {
        int count = factory.getCreditScoreLoanModel().getCreditScoreLoanCount();
        if(count <2)
        {
            apply();
        }
        else
        {
            view.alreadyRunningLoan(count);
        }
    }
    @Override
    public IApplyForCreditLoan setView(ICreditScoreLoanView view) {
        this.view=view;
        return this;
    }
    @Override
    public IApplyForCreditLoan setFactory(ICreditScoreLoanFactory factory) {
        this.factory=factory;
        return this;
    }

}
