package com.nord.view.loan;

import com.nord.common.Constants;
import com.nord.persistence.loan.interfaces.ILoanModel;
import com.nord.view.AbstractUserInterface;
import com.nord.view.interfaces.IMenuInterface;
import com.nord.view.loan.interfaces.ILoanView;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * This class implements all the methods that show the console output for the loan
 * feature, it also extends the generic methods required for each feature
 * @author Samir Anwar Rana
 */


public class LoanView extends AbstractUserInterface implements ILoanView {
    @Override
    public void showMenu() {
        showHeader();
        System.out.println("1. Apply for a loan");
        System.out.println("2. View existing loans");
        System.out.println("3. Return to previous menu");
    }

    @Override
    public void showHeader() {
        System.out.println("=============================================================");
        System.out.println("                          Loan Page           ");
        System.out.println("=============================================================");

    }

    @Override
    public void showLoanType() {
        System.out.println("1. Student");
        System.out.println("2. Personal");
        System.out.println("3. Business");
    }

    @Override
    public void showInvalidInput() {
        System.out.println("Please select a valid input");
    }

    @Override
    public Map<String, Object> createLoan(Map<String, Object> loanDetails){
        System.out.println("Please Enter Loan Details: ");

        for(Map.Entry<String, Object> item : loanDetails.entrySet()){
            System.out.println("Enter "+item.getKey()+" *");
            if(item.getKey().toLowerCase(Locale.ROOT).contains("type")){
                showLoanType();
                item.setValue(getIntegerInput());
            }  else if(item.getKey().toLowerCase(Locale.ROOT).contains("amount")){
                item.setValue(getDoubleInput());
            }
            else {
                item.setValue(getStringInputNoValidation());
            }
        }
        confirmDetails(loanDetails);
        return loanDetails;
    }

    @Override
    public void confirmDetails(Map<String, Object> loanDetails){
        showHeader();
        for(Map.Entry<String, Object> item : loanDetails.entrySet()){
            if(item.getKey().contains("Type")){
                if(item.getValue().equals(1)){
                    System.out.printf(Constants.TYPE_FORMAT, item.getKey(), "Student");
                } else if (item.getValue().equals(2)){
                    System.out.printf(Constants.TYPE_FORMAT, item.getKey(), "Personal");
                } else if (item.getValue().equals(3)){
                    System.out.printf(Constants.TYPE_FORMAT, item.getKey(), "Business");
                }
            } else {
                System.out.printf(Constants.TYPE_FORMAT, item.getKey(), item.getValue());
            }
        }
        System.out.println("\n1. Confirm loan details");
        System.out.println("2. Back to main menu");
    }

    @Override
    public void loanCreatedSuccessfully(){
        System.out.println("Loan has been sent to the admin for approval");
    }

    @Override
    public void showAllLoans(List<ILoanModel> loanModels) {
        showHeader();
        int count = 1;
        System.out.println("\nCurrent loans ");
        System.out.println("Id     Type       Amount     Applied Date    Repayment Date  " +
                "Interest       Status");
        System.out.println("==================================================================================");
        for (ILoanModel lm : loanModels) {
            String status;
            boolean loan = lm.getStatus();
            if(loan){
                status = "approved";
            } else{
                status = "requesting approval";
            }

            if(lm.getLoanType().contains("Student")){
                System.out.println(count++ + ".  " + lm.getLoanType()+ "         "
                        + lm.getAmount() + "      " +lm.getAppliedDate() + "      "
                        + lm.getRepaymentDate() + "       " +lm.getInterestRate() + "     " + status);
            } else {
                System.out.println(count++ + ".  " + lm.getLoanType()+ "        "
                        + lm.getAmount() + "      " +lm.getAppliedDate() + "      "
                        + lm.getRepaymentDate() + "       " +lm.getInterestRate() + "     " + status);
            }

        }
        System.out.println("=================================================================================");
        System.out.println("\n0. Back to main menu");
    }


}


