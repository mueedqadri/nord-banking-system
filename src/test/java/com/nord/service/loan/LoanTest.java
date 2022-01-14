package com.nord.service.loan;

import com.nord.persistence.DbConnection;
import com.nord.service.Context;
import com.nord.service.loan.interfaces.ILoanService;
import com.nord.service.loan.mock.LoanMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class LoanTest {
    private final int TEST_USER_ID = 2;
    ILoanService loanService = new LoanService();
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    void addLoanTest(){
        Context.setLoggedInUserId(TEST_USER_ID);
        LoanMock loanMock = new LoanMock();
        assertTrue(loanService.handleSuccessfulLoanPaymentCreation(loanMock.addLoan()));
    }

    @Test
    void HomePageTest(){
        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Context.setLoggedInUserId(TEST_USER_ID);
        ILoanService loan = new LoanService();
        loan.LoanHomePage();
        assertTrue(true);

    }


    @AfterEach
    public void tearDown() {
        DbConnection.closeDbConnection();
    }
}
