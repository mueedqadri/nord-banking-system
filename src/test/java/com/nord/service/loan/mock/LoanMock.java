package com.nord.service.loan.mock;

import java.util.HashMap;
import java.util.Map;

public class LoanMock {
    public Map<String, Object> addLoan() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("Loan Type", 1);
        obj.put("Amount", 100.00);
        return obj;
    }
}