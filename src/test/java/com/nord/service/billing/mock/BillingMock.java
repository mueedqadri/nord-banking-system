package com.nord.service.billing.mock;

import java.time.LocalDate;
import java.util.*;

public class BillingMock {
  public Map<String, Object> addBill() {
    Map<String, Object> obj = new HashMap<>();
    Calendar calendar = Calendar.getInstance();
    calendar.set(2021, 07, 21, 59, 59, 59);
    LocalDate date = LocalDate.now();
    obj.put("Payee Name", "Morgan");
    obj.put("Account Number", "123456");
    obj.put("Payment Info", "Phone Bill");
    obj.put("Amount", 12.00);
    obj.put("Payment Date", date);
    obj.put("Payment Cycle", 2);
    return obj;
  }
}
