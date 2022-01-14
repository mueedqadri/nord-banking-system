package com.nord.service.loan;

import com.nord.persistence.loan.LoanApprovalModel;
import com.nord.persistence.loan.interfaces.ILoanApprovalModel;
import com.nord.service.loan.interfaces.ILoanApproval;
import com.nord.view.loan.ApproveLoanView;
import com.nord.view.loan.interfaces.IApproveLoanView;

/**
 * This class is responsible viewing loans that require approval by admin
 * @author Samir Anwar Rana
 */

public class LoanApproval implements ILoanApproval {

    private IApproveLoanView view = new ApproveLoanView();

    @Override
    public void viewApprovalLoans() {
        ILoanApprovalModel model = new LoanApprovalModel();
        view.showAllLoans(model.retrieveLoans());
        int input = view.getIntegerInput();
        if (input != 0) {
            model.approveLoan(input);
            view.loanApproved();
            int secondInput = view.getIntegerInput();
            if(secondInput != 0){
                view.showInvalidInputNumber();
            }
        }
    }
}
