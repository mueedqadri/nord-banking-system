package com.nord.service.billing;

import com.nord.persistence.DbConnection;
import com.nord.service.Context;
import com.nord.service.billing.interfaces.IBillingService;
import com.nord.service.billing.mock.BillingMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class BillingTest {
    private final int TEST_USER_ID = 2;
    IBillingService billingService = new BillingService();
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    @Test
    void addBillTest(){
        Context.setLoggedInUserId(TEST_USER_ID);
        BillingMock billingMock = new BillingMock();
        assertTrue(billingService.handleSuccessfulBillPaymentCreation(billingMock.addBill()));
    }

    @Test
    void deleteBillTest(){
        Context.setLoggedInUserId(TEST_USER_ID);
        assertTrue(billingService.handleDeletionOfBill("Morgan"));
    }

    @Test
    void HomePageTest(){
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Context.setLoggedInUserId(TEST_USER_ID);
        IBillingService bill = new BillingService();
        bill.billingHomePage();
        String actualOutPut = outputStreamCaptor.toString()
                .replace("\n", "").replace("\r", "");
        assertTrue(actualOutPut.contains("Add a new bill payment"));

    }


    @AfterEach
    public void tearDown() {
        DbConnection.closeDbConnection();
    }
}
