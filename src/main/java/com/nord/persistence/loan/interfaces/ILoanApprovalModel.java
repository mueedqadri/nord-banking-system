package com.nord.persistence.loan.interfaces;

import java.util.List;

public interface ILoanApprovalModel {
    List<ILoanModel> retrieveLoans();

    boolean approveLoan(int loanId);
}
