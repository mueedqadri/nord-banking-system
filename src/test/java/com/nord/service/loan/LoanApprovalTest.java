package com.nord.service.loan;

import com.nord.service.Context;
import com.nord.service.loan.interfaces.ILoanApproval;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class LoanApprovalTest{

    private final int TEST_USER_ID = 2;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void approveLoan(){
        String input = "1 0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Context.setLoggedInUserId(TEST_USER_ID);
        ILoanApproval loan = new LoanApproval();
        loan.viewApprovalLoans();
        String actualOutPut = outputStreamCaptor.toString()
                .replace("\n", "").replace("\r", "");
        assertTrue(actualOutPut.contains("Loan has been approved"));
    }
}
