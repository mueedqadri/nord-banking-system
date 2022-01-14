package com.nord.service.creditCardServicesAndRecommendation.interfaces;

import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardServicesAndRecommendationView;

public interface IUpgradeForCreditCard extends IAssignCreditCardToUser {

  IUpgradeForCreditCard setView(ICreditCardServicesAndRecommendationView view);

  IUpgradeForCreditCard setFactory(ICreditCardRecommendationFactory factory);
}
