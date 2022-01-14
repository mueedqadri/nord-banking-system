package com.nord.service.billing;

import com.nord.persistence.DbConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class AutomaticBillPaymentTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void automaticBillPaymentJob(){
        AutomaticBillPaymentService bill = new AutomaticBillPaymentService(12);
        bill.run();
        String actualOutPut = outputStreamCaptor.toString()
                .replace("\n", "").replace("\r", "");
        assertTrue(actualOutPut.contains("has been deducted from your account"));
    }

    @AfterEach
    public void tearDown() {
        DbConnection.closeDbConnection();
    }
}
