package com.nord.service.creditCardServicesAndRecommendation.interfaces;


import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardServicesAndRecommendationView;
import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardTypesView;

public interface IRecommendForCreditCard {

  /**
  *This method retrieves some data from the user such as age, salary, spending and
  *recommends the credit card according to that
  *@param
   */
  void recommendCreditCard();
  IRecommendForCreditCard setView(ICreditCardServicesAndRecommendationView view);
  IRecommendForCreditCard setCreditCardTypeView(ICreditCardTypesView typesView);
}
