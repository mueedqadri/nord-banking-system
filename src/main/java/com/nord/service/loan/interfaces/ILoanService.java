package com.nord.service.loan.interfaces;

import java.util.Map;

/**
 * This interface consist of the home page for the loan feature and the handlers that are used to perform
 * loan operations
 * @author Samir Anwar Rana
 */

public interface ILoanService {

    boolean handleSuccessfulLoanPaymentCreation(Map<String,Object> loanDetails);

    void LoanHomePage();
}
