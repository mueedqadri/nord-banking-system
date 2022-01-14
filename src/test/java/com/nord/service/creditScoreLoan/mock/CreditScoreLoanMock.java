package com.nord.service.creditScoreLoan.mock;

/**
 *This is the Credit Score Loan Mock for Credit Score Loan feature
 * @author Harit Patwa
 */

public class CreditScoreLoanMock {

  public String MockHomePage() {
    StringBuilder sb=new StringBuilder();
    sb.append("=============================================================");
    sb.append("                      Credit Score Loan Menu           ");
    sb.append("=============================================================");
    sb.append("1. View your credit loan history");
    sb.append("2. Apply for credit loan");
    sb.append("3. Pay your credit loan");
    sb.append("4. Tips for improving credit loans");
    sb.append("5. Return to main menu");
    return sb.toString();
  }
  public String MockTipsPage()
  {
    StringBuilder sb=new StringBuilder();
    sb.append("For improving you crs score please follow below steps");
    sb.append("Always pay your loan before deadline");
    sb.append("Do not have multiple loans running at same time");
    sb.append("Once you have poor Credit score rating you will have to wait 1 year to improve it");
    sb.append("Press 1. to return to previous menu");
    return sb.toString();
  }
  public String MockShowLoanHistory()
  {
    StringBuilder sb=new StringBuilder();
   sb.append("Sorry! You haven't taken the Credit Loan before");
   sb.append("Press 1. to return to previous menu");
           return sb.toString();
  }
  public String MockApplyForAll()
  {
    StringBuilder sb=new StringBuilder();
   sb.append("Sorry your credit history is very poor, we cannot allow you credit loan");
   sb.append("Press 1. to return to previous menu");
           return sb.toString();
  }
  public String MockPayCreditLoan()
  {
    StringBuilder sb=new StringBuilder();
   sb.append("You have no outstanding loan payment due");
   sb.append("Press 1. to return to previous menu");
           return sb.toString();
  }
  public String MockApplyDecent()
  {
    StringBuilder sb=new StringBuilder();
    sb.append("You are already having 4 loans running, you are not allowed for more");
    return sb.toString();
  }
  public String MockApplyExceptional()
  {
    StringBuilder sb=new StringBuilder();
    sb.append("You are already having 4 loans running, you are not allowed for more");
    return sb.toString();
  }

}
