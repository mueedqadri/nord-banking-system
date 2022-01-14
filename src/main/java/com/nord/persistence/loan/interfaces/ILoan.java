package com.nord.persistence.loan.interfaces;

import java.util.List;
import java.util.Map;

public interface ILoan {
    boolean addLoan(Map<String, Object> loanDetails);

    List<ILoanModel> retrieveLoansForUser();
}
