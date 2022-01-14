package com.nord.service.creditCardServicesAndRecommendation.interfaces;

import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardServicesAndRecommendationView;

public interface IPayForCreditCard {

  /**
  *This method is handling the credit card bill payment mechanism, it handles user inputs
  *and provides user with an option to pay the credit card bill
  *@param
   */
  void payCreditCardBill();
  IPayForCreditCard setView(ICreditCardServicesAndRecommendationView view);
  IPayForCreditCard setFactory(ICreditCardRecommendationFactory factory);
}
