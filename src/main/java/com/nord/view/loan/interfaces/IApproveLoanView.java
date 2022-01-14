package com.nord.view.loan.interfaces;

import com.nord.persistence.loan.interfaces.ILoanModel;
import com.nord.view.interfaces.IUserInterface;

import java.util.List;

public interface IApproveLoanView extends IUserInterface {
    void loanApproved();

    void showAllLoans(List<ILoanModel> loanModels);
}
