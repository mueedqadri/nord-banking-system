package com.nord.view.creditCardServicesAndRecommendation.interfaces;

import com.nord.persistence.creditCardServicesAndRecommendation.interfaces.ICreditCardDetails;

import java.util.List;

public interface ICreditCardServicesAndRecommendationView {

  void showMenu();
  void returnMenu();
  int getIntegerInput();
  void showInvalidInput();
  void showHeader();
  void listCreditCard();
  String getCreditCardName(int index);
  void creditCardHeading(String heading);
  int getCreditCardFees(int input);
  String getCreditCardDetails(int input);
  void creditCardDetails(int i, String s);
  void congratulationsText(String creditCardName);
  void invalidOption();
  void showUnpaidBills(List<ICreditCardDetails> unpaidBills);
  void congratulationsForBillPay();
  void notEnoughBalance();
  void upgradeFromList();
  void noCreditCardPlan();
  void alreadyAppliedCard();
  void congratulationsUpgradeText(String creditCardName);
  void initialLine();
  void enterAge();
  int getValidAge();
  void enterSalary();
  int getValidSalary();
  void enterSpending();
  int getvalidSpending();
  void recommendCard(String creditCardName);
  void noRecommendation();
}
