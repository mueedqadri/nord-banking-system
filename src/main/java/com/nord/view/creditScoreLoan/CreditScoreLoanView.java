package com.nord.view.creditScoreLoan;

import com.nord.persistence.creditScoreLoan.interfaces.ICreditScoreLoanModel;
import com.nord.view.AbstractUserInterface;
import com.nord.view.creditScoreLoan.interfaces.ICreditScoreLoanView;
import com.nord.view.interfaces.IMenuInterface;

import java.util.List;

/**
 * This class is responsible for Credit Score Loan feature's presentation layer
 * @author Harit Patwa
 */

public class CreditScoreLoanView extends AbstractUserInterface implements IMenuInterface, ICreditScoreLoanView {
    @Override
    public void showMenu() {
        showHeader();
        System.out.println("1. View your credit loan history");
        System.out.println("2. Apply for credit loan");
        System.out.println("3. Pay your credit loan");
        System.out.println("4. Tips for improving credit loans");
        System.out.println("5. Return to main menu");

    }

    @Override
    public void showHeader() {
        System.out.println("=============================================================");
        System.out.println("                      Credit Score Loan Menu           ");
        System.out.println("=============================================================");
    }

    @Override
    public void showInvalidInput() {
        System.out.println("Something went wrong. Please try again!");
    }
    public void noLoanBefore()
    {
        System.out.println("Sorry! You haven't taken the Credit Loan before");
    }
    public void noPendingLoan() { System.out.println("You have no outstanding loan payment due");}

    public void showPreviousLoans(List<ICreditScoreLoanModel> creditScoreLoanModels)
    {
        showHeader();
        if(!creditScoreLoanModels.isEmpty())
        {
            showPreviousLoansLine();
            for(ICreditScoreLoanModel cslm: creditScoreLoanModels)
            {
                System.out.println(" Amount : "+ cslm.getAmount()+" Due Date: "+cslm.getDueDate()+" Pay status: "+cslm.getPaidStatus());
            }

        }
        else {
            noLoanBefore();
        }
        returnMenu();

    }
    public void showPreviousLoansLine()
    {
        System.out.println("You have taken the credit score loan before, here is your history");
    }
    public void poorCreditScore()
    {
        System.out.println("Sorry your credit history is very poor, we cannot allow you credit loan");
    }
    public void decentCreditScore()
    {
        System.out.println("Your credit history is decent, We can allow you 1 loan at a time");
    }
    public void exceptionalCreditScore()
    {
        System.out.println("Your credit history is exceptional, we will allow you 2 loans at a time");
    }
    public void alreadyRunningLoan(int loanNumber)
    {
        if((loanNumber <= 1)) {
            System.out.println("You are already having " + loanNumber + " loan running please complete the payment first");
        } else {
            System.out.println("You are already having " + loanNumber + " loans running, you are not allowed for more");
        }
    }
    public void enterAmount()
    {
        System.out.println("PLease enter amount between 1-1000 CAD");
    }

    public void congratulationForLoanGranted(String strDate)
    {
        System.out.println("Congratulations, You have got the loan, amount is added to your main balance");
        System.out.println("Make sure you pay your loan before "+strDate);
    }
    public void payLoan(List<ICreditScoreLoanModel> creditScoreLoanModels)
    {
        int count =1;

        if(!creditScoreLoanModels.isEmpty())
        {

            for(ICreditScoreLoanModel cslm: creditScoreLoanModels)
            {
                if(!cslm.getPaidStatus())
                {
                    System.out.println(count+". Amount : "+ cslm.getAmount()+" Due Date: "+cslm.getDueDate()+" Pay status: "+cslm.getPaidStatus());
                    count++;
                }
            }

        }
        else {
            noLoanBefore();
        }
        System.out.println(count + ". Return to previous page");
    }
    public void selectLoanLine()
    {
        System.out.println("Please select which loan you want to pay");
    }
    public void loanPaymentSuccess()
    {
        System.out.println("Congratulations you have successfully paid the loan amount");
    }
    public void noBalance()
    {
        System.out.println("You don't have enough balance to pay this loan");
    }
    public void tipsForImprove()
    {
        System.out.println("For improving you crs score please follow below steps");
        System.out.println("Always pay your loan before deadline");
        System.out.println("Do not have multiple loans running at same time");
        System.out.println("Once you have poor Credit score rating you will have to wait 1 year to improve it");
    }

    public void returnMenu()
    {
        System.out.println("Press 1. to return to previous menu");
    }
    public void returnMenu(int input)
    {
        System.out.println("Press "+input+". to return to previous menu");
    }

}
