package com.nord.service.creditCardServicesAndRecommendation.interfaces;

import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardServicesAndRecommendationView;

public interface IApplyForCreditCard extends IAssignCreditCardToUser{

  IApplyForCreditCard setView(ICreditCardServicesAndRecommendationView view);

  IApplyForCreditCard setFactory(ICreditCardRecommendationFactory factory);
}
