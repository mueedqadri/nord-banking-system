package com.nord.view.creditCardServicesAndRecommendation;

import com.nord.persistence.creditCardServicesAndRecommendation.interfaces.ICreditCardDetails;
import com.nord.view.AbstractUserInterface;
import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardServicesAndRecommendationView;
import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardTypesView;
import com.nord.view.interfaces.IMenuInterface;

import java.util.List;

/**
 * This class is representing presentation layer of Credit Card Services and Recommendation Feature
 * @author Harit Patwa
 */
public class CreditCardServicesAndRecommendationView extends AbstractUserInterface implements ICreditCardServicesAndRecommendationView, IMenuInterface {

  private ICreditCardTypesView creditCardTypesView;
  public CreditCardServicesAndRecommendationView()
  { creditCardTypesView= new CreditCardTypesView(); }

  @Override
  public void showMenu() {
    showHeader();
    System.out.println("1. Pay your credit card bills");
    System.out.println("2. Upgrade your current credit card");
    System.out.println("3. Apply for new credit card");
    System.out.println("4. Suggest me credit card");
  }
  @Override
  public void showHeader() {
    System.out.println("=============================================================");
    System.out.println("                      Credit card section           ");
    System.out.println("=============================================================");
  }
  @Override
  public void showInvalidInput() {
    System.out.println("Something went wrong. Please try again!");
  }

  public void listCreditCard() {
    System.out.println("1. MoneyBack Credit Card (for more details press 1)");
    System.out.println("2. Gold Credit Card (for more details press 2)");
    System.out.println("3. Millennia Credit Card (for more details press 3)");
    System.out.println("4. Titanium Credit Card (for more details press 4)");
    System.out.println("5. Platinum Credit Card (for more details press 5)");
  }
  public void creditCardHeading(String heading) {
    System.out.println("=============================================================");
    System.out.println("                      " + heading + "           ");
    System.out.println("=============================================================");
  }
  public void creditCardDetails(int annulFee, String banefits) {
    System.out.println("Annual Fee: " + annulFee + " CAD");
    System.out.println(banefits);
    System.out.println();
    System.out.println("1. Apply ");
    System.out.println("9. Return to main menu");
  }
  public void congratulationsText(String cardName) {
    System.out.println("Congratulations you have successfully applied " + cardName);
  }
  public void congratulationsUpgradeText(String cardName) {
    System.out.println("Congratulations you have successfully upgraded to  " + cardName);
  }
  public void returnMenu() {
    System.out.println("Press 9. to return back to main menu");
  }
  public void showUnpaidBills(List<ICreditCardDetails> unPaidBills) {
    System.out.println("Here is your unpaid bill list");
    int count = 1;
    for(int i = 0; i < unPaidBills.size(); i++) {
      System.out.println(count + ". Bill Amount: " + unPaidBills.get(i).getAmount() + " Due Date: " + unPaidBills.get(i).getDate());
      count++;
    }
    System.out.println("Press " + (unPaidBills.size() + 1) + ". to return to previous menu");
  }
  public void congratulationsForBillPay() {
    System.out.println("Congratulatons, Your bill has been paid successfully!");
  }
  public void notEnoughBalance() {
    System.out.println("Your bank balance isn't sufficient to pay the Credit Card Bill");
  }
  public void upgradeFromList() {
    System.out.println("Please choose any of the plan from the given list below");
  }
  public void initialLine() {
    System.out.println("We will try to recommend you best credit card according to your usage");
  }
  public void enterAge() {
    System.out.println("Please Enter your age");
  }
  public void enterSalary() {
    System.out.println("Please Enter your salary");
  }
  public void enterSpending()
  {
    System.out.println("Please Enter your spending");
  }
  public void recommendCard(String cardName)
  {
    System.out.println("We recommend you: "+cardName);
  }
  public void noRecommendation()
  {
    System.out.println("Sorry no card available in our system available for your requirements");
  }
  public void invalidOption()
  {
    System.out.println("Sorry you already have one credit card, You cannot apply for another");
    System.out.println("Please visit credit card plan upgrade menu");
  }
  public void noCreditCardPlan()
  {
    System.out.println("Sorry you have no credit card, You need to apply for credit card first");
    System.out.println("Please visit credit card plan apply menu");
  }
  public void alreadyAppliedCard()
  {
    System.out.println("Sorry you already have same credit card plan, You cannot upgrade it");
    System.out.println("Please choose different credit card plan in upgrade menu");
  }
  public String getCreditCardName(int index)
  {
    return creditCardTypesView.getCreditCardName(index);
  }
  public String getCreditCardDetails(int index)
  {
    return  creditCardTypesView.getCreditCardDetails(index);
  }
  public int getCreditCardFees(int index)
  {
   return creditCardTypesView.getCreditCardFees(index);
  }

}
