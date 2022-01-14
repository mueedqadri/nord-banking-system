package com.nord.service.creditCardServicesAndRecommendation;

import com.nord.service.creditCardServicesAndRecommendation.interfaces.IAssignCreditCardToUser;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.ICreditCardRecommendationFactory;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.IUpgradeForCreditCard;
import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardServicesAndRecommendationView;

/**
 * This class represents Upgrade credit Card feature
 * It implements assignCreditCardToUser method which assigns credit card to user
 * @author Harit Patwa
 */
public class UpgradeForCreditCard implements IUpgradeForCreditCard, IAssignCreditCardToUser {

  private ICreditCardRecommendationFactory factory;
  private ICreditCardServicesAndRecommendationView view;

  public UpgradeForCreditCard() {  }

  @Override
  public IUpgradeForCreditCard setView(ICreditCardServicesAndRecommendationView view) {
    this.view = view;
    return this;
  }

  @Override
  public IUpgradeForCreditCard setFactory(ICreditCardRecommendationFactory factory) {
    this.factory = factory;
    return this;
  }

  @Override
  public void assignCreditCardToUser() {
    view.showHeader();
    view.upgradeFromList();
    view.listCreditCard();
    view.returnMenu();
    int input = view.getIntegerInput();
    if (input >0 && input <6)
    {
      view.creditCardHeading(view.getCreditCardName(input));
      view.creditCardDetails(view.getCreditCardFees(input),view.getCreditCardDetails(input));
      int index =input;
      input = view.getIntegerInput();
      if (input == 1)
      {
        if( (factory.getCreditCardDetailsModel().getCreditCardDetails()==null)) {
          view.noCreditCardPlan();
        }
        else if( factory.getCreditCardDetailsModel().getCreditCardDetails().equals(view.getCreditCardName(index))) {
          view.alreadyAppliedCard();
        } else {
          factory.getCreditCardDetailsModel()
                  .setCreditCardDetails(view.getCreditCardName(index));
          view.congratulationsUpgradeText(view.getCreditCardName(index));
        }
        view.returnMenu();
        input = view.getIntegerInput();
      }
      while(input!=9)
      {
        view.showInvalidInput();
        view.returnMenu();
        input=view.getIntegerInput();
      }
    }
  }
}
