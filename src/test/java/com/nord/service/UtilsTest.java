package com.nord.service;

import com.nord.persistence.DbConnection;
import com.nord.common.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * Test methods in utils
 * @author Abdul Mueed Qadri
 */

class UtilsTest {
    @Test
    void daysConvertCheckDays() {
        assertTrue(Utils.daysConvertToString(25).equals("25 days "));
    }

    @Test
    void daysConvertCheckSingleDay() {
        assertTrue(Utils.daysConvertToString(1).equals("1 day "));
    }

    @Test
    void calculateProfit() {
        assertEquals(10.0, Utils.calculateProfit(10, 100), 2);
    }

    @AfterEach
    public void tearDown() {
        DbConnection.closeDbConnection();
    }
}