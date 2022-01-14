package com.nord.service.creditCardServicesAndRecommendation;


import com.nord.persistence.creditCardServicesAndRecommendation.CreditCardBills;
import com.nord.persistence.creditCardServicesAndRecommendation.CreditCardDetailsModel;
import com.nord.persistence.creditCardServicesAndRecommendation.interfaces.ICreditCardBills;
import com.nord.persistence.creditCardServicesAndRecommendation.interfaces.ICreditCardDetailsModel;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.*;

/**
 * This class represents factory methods for Credit Card Services and Recommendation feature
 * It retrieves methods according to the sub method's requirement.
 * @author Harit Patwa
 */

public class CreditCardRecommendationFactory implements ICreditCardRecommendationFactory {

  @Override
  public IApplyForCreditCard applyForCreditCard() {
    return new ApplyForCreditCard();
  }

  @Override
  public IPayForCreditCard payCreditCardBill() {
    return new PayForCreditCard();
  }

  @Override
  public IUpgradeForCreditCard upgradeCreditCard() {
    return new UpgradeForCreditCard();
  }

  @Override
  public IRecommendForCreditCard recommendCreditCard() {
    return new RecommendForCreditCard();
  }

  @Override
  public ICreditCardBills getCreditCardBills() {
    return new CreditCardBills();
  }

  @Override
  public ICreditCardDetailsModel getCreditCardDetailsModel() {
    return new CreditCardDetailsModel();
  }
}
