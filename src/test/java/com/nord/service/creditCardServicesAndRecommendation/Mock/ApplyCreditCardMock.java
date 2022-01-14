package com.nord.service.creditCardServicesAndRecommendation.Mock;

public class ApplyCreditCardMock {


  public String ApplyCreditCard() {
    StringBuilder sb=new StringBuilder();
    sb.append("=============================================================");
    sb.append("                      Credit card section           ");
    sb.append("=============================================================");
    sb.append("1. MoneyBack Credit Card (for more details press 1)");
    sb.append("2. Gold Credit Card (for more details press 2)");
    sb.append("3. Millennia Credit Card (for more details press 3)");
    sb.append("4. Titanium Credit Card (for more details press 4)");
    sb.append("5. Platinum Credit Card (for more details press 5)");
    sb.append("Press 9. to return back to main menu");
    sb.append("=============================================================");
    sb.append("                      MoneyBack Credit Card           ");
    sb.append("=============================================================");
    sb.append("Annual Fee: 0 CAD");
    sb.append("Accelerated Moneyback rewards for students. ");
    sb.append("Interest rates: 19.99% purchases /");
    sb.append("                 22.99% cash advances");
    sb.append("1. Apply ");
    sb.append("9. Return to main menu");
    sb.append("Sorry you already have one credit card, You cannot apply for another");
    sb.append("Please visit credit card plan upgrade menu");
    sb.append("Press 9. to return back to main menu");
    return sb.toString();
  }
}
