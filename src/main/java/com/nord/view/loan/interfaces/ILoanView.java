package com.nord.view.loan.interfaces;

import com.nord.persistence.loan.interfaces.ILoanModel;
import com.nord.view.interfaces.IUserInterface;

import java.util.List;
import java.util.Map;

public interface ILoanView extends IUserInterface {
    void showMenu();

    void showHeader();

    void showLoanType();

    void showInvalidInput();

    Map<String, Object> createLoan(Map<String, Object> loanDetails);

    void confirmDetails(Map<String, Object> loanDetails);

    void loanCreatedSuccessfully();

    void showAllLoans(List<ILoanModel> loanModels);
}
