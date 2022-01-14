package com.nord.service.loan;

import com.nord.common.Constants;
import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlanPersistence;
import com.nord.persistence.loan.Loan;
import com.nord.persistence.loan.interfaces.ILoan;
import com.nord.service.fixedDeposit.FixedDeposit;
import com.nord.service.fixedDeposit.interfaces.IFixedDepositFactory;
import com.nord.service.loan.interfaces.ILoanService;
import com.nord.service.transactionHistory.TransactionHistoryService;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositMainView;
import com.nord.view.loan.LoanView;
import com.nord.view.loan.interfaces.ILoanView;
import com.nord.view.transactionHistory.interfaces.ITransactionHistoryView;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class is responsible for performing the functions of the loan feature
 * @author Samir Anwar Rana
 */


public class LoanService implements ILoanService {
    protected ILoanView view = new LoanView();
    protected ILoan model = new Loan();
    protected ILoanService factory;

    public LoanService setView(ILoanView view) {
        this.view = view;
        return this;
    }

    public LoanService setFactory(ILoanService factory) {
        this.factory = factory;
        return this;
    }

    @Override
    public void LoanHomePage() {
        view.showMenu();
        while (true) {
            int input = view.getIntegerInput();
            switch (input) {
                case 1:
                    createLoan();
                    return;
                case 2:
                    viewLoans();
                    return;
                case 3:
                    return;
            }
        }
    }

    private void createLoan() {
        int input;
        Map<String, Object> loanDetails = new LinkedHashMap<String, Object>() {{
            put(Constants.LOAN_TYPE, null);
            put(Constants.AMOUNT, null);
        }};
        while (true) {
            loanDetails = view.createLoan(loanDetails);
            input = view.getIntegerInput();
            if (input == 1) {
                handleSuccessfulLoanPaymentCreation(loanDetails);
                return;
            } else if (input == 2) {
                return;
            } else {
                view.showInvalidInput();
            }
        }
    }

    @Override
    public boolean handleSuccessfulLoanPaymentCreation(Map<String, Object> loanDetails){
        boolean result = false;
        if(model.addLoan(loanDetails)){
            view.loanCreatedSuccessfully();
            return true;
        }
        return result;
    }

    private void viewLoans() {
        view.showAllLoans(model.retrieveLoansForUser());
    }

}
