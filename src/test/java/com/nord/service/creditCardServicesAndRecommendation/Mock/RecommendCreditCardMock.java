package com.nord.service.creditCardServicesAndRecommendation.Mock;

public class RecommendCreditCardMock {
  StringBuilder sb;

  public String recommendCreditCard() {
    sb=new StringBuilder();
    sb.append("=============================================================");
    sb.append("                      Credit card section           ");
    sb.append("=============================================================");
    sb.append("We will try to recommend you best credit card according to your usage");
    sb.append("Please Enter your age");
    sb.append("Please Enter your salary");
    sb.append("Please Enter your spending");
    sb.append("We recommend you: Millennia Credit Card");
    sb.append("Press 9. to return back to main menu");
    return sb.toString();
  }
}
