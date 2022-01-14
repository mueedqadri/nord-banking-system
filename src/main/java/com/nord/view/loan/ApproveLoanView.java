package com.nord.view.loan;

import com.nord.persistence.loan.interfaces.ILoanModel;
import com.nord.view.loan.interfaces.IApproveLoanView;
import com.nord.view.userManagement.AdminMainMenuView;

import java.util.List;

/**
 * This class is responsible for displaying all the console messages related to the loan approval feature
 * of the admin side
 * @author Samir Anwar Rana
 */

public class ApproveLoanView extends AdminMainMenuView implements IApproveLoanView {

    @Override
    public void loanApproved(){
        System.out.println("Loan has been approved ");
        System.out.println("0. Back to main menu");
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
        System.out.println("Type the number of the loan you wish to approve ");
    }
}
