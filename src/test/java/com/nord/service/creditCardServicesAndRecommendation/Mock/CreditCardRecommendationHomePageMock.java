package com.nord.service.creditCardServicesAndRecommendation.Mock;

public class CreditCardRecommendationHomePageMock {

  public String showHomepageMock() {
    StringBuilder sb=new StringBuilder();
    sb.append("=============================================================");
    sb.append("                      Credit card section           ");
    sb.append("=============================================================");
    sb.append("1. Pay your credit card bills");
    sb.append("2. Upgrade your current credit card");
    sb.append("3. Apply for new credit card");
    sb.append("4. Suggest me credit card");
    sb.append("Press 9. to return back to main menu");
    return sb.toString();
  }
}
