package com.nord.view.fixedDeposit;

import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.common.Utils;
import com.nord.view.AbstractUserInterface;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Generic Fixed Deposit view
 * @author Abdul Mueed Qadri
 */
public abstract class AbstractFixedDepositView extends AbstractUserInterface implements IFixedDepositView {

    @Override
    public void showHeader() {
        System.out.println("=============================================================");
        System.out.println("                        Fixed Deposit                          ");
        System.out.println("=============================================================");
    }

    @Override
    public void showDateInput() {
        System.out.println("Please Enter the Withdrawal Date (dd/MM/yyyy) ");
    }

    @Override
    public void showChooseCorrectPlan() {
        System.out.println("Date entered does not match the current plan");
    }

    @Override
    public void showFdSummary(IUserFixedDepositModel fd) {
        int fdDurationDays = (int) ChronoUnit.DAYS.between(LocalDate.now(), fd.getWithdrawalDate());
        System.out.println("* Interest Rate: " + fd.getInterest() + "%");
        System.out.println("* Withdrawal Date: " + fd.getWithdrawalDate());
        System.out.println("* Amount deposited: $" + fd.getAmount());
        System.out.println("* Amount after due date: $" + (fd.getAmount() + fd.getProfit()));
        System.out.println("* Profit after due date: $" + fd.getProfit());
        System.out.println("* Duration of FD: " + Utils.daysConvertToString(fdDurationDays));
    }

    @Override
    public void notEnoughBalance(){
        System.out.println("You account balance is less than Fd Amount. " +
                "Please add funds to your account");
    }

    @Override
    public void showAmount() {
        System.out.println("Please Enter the amount ");
    }

}
