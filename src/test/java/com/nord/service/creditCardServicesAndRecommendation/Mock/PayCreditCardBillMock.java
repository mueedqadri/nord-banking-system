package com.nord.service.creditCardServicesAndRecommendation.Mock;

public class PayCreditCardBillMock {


  public String payBillMock() {
    StringBuilder sb=new StringBuilder();
    sb.append("=============================================================");
    sb.append("                      Credit card section           ");
    sb.append("=============================================================");
    sb.append("Here is your unpaid bill list");
    sb.append("Press 1. to return to previous menu");
    return sb.toString();
  }
}
