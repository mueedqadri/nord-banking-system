package com.nord.service.creditCardServicesAndRecommendation;

import com.nord.common.Constants;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.IRecommendForCreditCard;
import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardServicesAndRecommendationView;
import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardTypesView;

/**
 * This class handles recommendation of the credit card to the user
 * It has recommend method which handles business logic for recommending credit card
 * @author Harit Patwa
 */
public class RecommendForCreditCard implements IRecommendForCreditCard {

  private ICreditCardTypesView creditCardTypesView;
  private ICreditCardServicesAndRecommendationView view;

  @Override
  public IRecommendForCreditCard setView(ICreditCardServicesAndRecommendationView view) {
    this.view = view;
    return this;
  }

  @Override
  public IRecommendForCreditCard setCreditCardTypeView(ICreditCardTypesView typesView) {
    this.creditCardTypesView = typesView;
            return this;
  }

  @Override
  public void recommendCreditCard() {
    view.showHeader();
    view.initialLine();
    view.enterAge();
    int age = view.getValidAge();
    view.enterSalary();
    int salary= view.getValidSalary();
    view.enterSpending();
    int spending = view.getvalidSpending();
    if(age<Constants.MAXIMUM_AGE_MONEYBACK && spending <Constants.MAXIMUM_SPENDING_MONEYBACK)
    {
      view.recommendCard(creditCardTypesView.getCreditCardName(Constants.INDEX_ONE));
    }else if(salary < Constants.GOLD_CARD_SALARY_LIMIT && spending <Constants.GOLD_CARD_SPENDING_LIMIT)
    {
      view.recommendCard(creditCardTypesView.getCreditCardName(Constants.INDEX_TWO));
    }else if(salary <Constants.MILLENNIA_CARD_SALARY_LIMIT && spending <Constants.MILLENNIA_CARD_SPENDING_LIMIT)
    {
      view.recommendCard(creditCardTypesView.getCreditCardName(Constants.INDEX_THREE));
    }else if(salary <Constants.TITANIUM_CARD_SALARY_LIMIT && spending <Constants.TITANIUM_CARD_SPENDING_LIMIT)
    {
      view.recommendCard(creditCardTypesView.getCreditCardName(Constants.INDEX_FOUR));
    }else if(salary <Constants.PLATINUM_CARD_SALARY_LIMIT && spending <Constants.PLATINUM_CARD_SPENDING_LIMIT)
    {
      view.recommendCard(creditCardTypesView.getCreditCardName(Constants.INDEX_FIVE));
    }
    else {
      view.noRecommendation();
    }
    view.returnMenu();
    int input = view.getIntegerInput();
    while(input!=9)
    {
      view.showInvalidInput();
      view.returnMenu();
      input=view.getIntegerInput();
    }
  }
}
