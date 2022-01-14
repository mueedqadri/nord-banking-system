package com.nord.service.creditCardServicesAndRecommendation.interfaces;


import com.nord.persistence.creditCardServicesAndRecommendation.interfaces.ICreditCardBills;
import com.nord.persistence.creditCardServicesAndRecommendation.interfaces.ICreditCardDetailsModel;

public interface ICreditCardRecommendationFactory{

  IApplyForCreditCard applyForCreditCard();

  IPayForCreditCard payCreditCardBill();

  IUpgradeForCreditCard upgradeCreditCard();

  IRecommendForCreditCard recommendCreditCard();

  ICreditCardBills getCreditCardBills();

  ICreditCardDetailsModel getCreditCardDetailsModel();
}
