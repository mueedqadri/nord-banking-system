package com.nord.service.creditCardServicesAndRecommendation;

import com.nord.service.creditCardServicesAndRecommendation.interfaces.IApplyForCreditCard;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.IAssignCreditCardToUser;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.ICreditCardRecommendationFactory;
import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardServicesAndRecommendationView;

/**
 * This class is single purpose class, which lets user to apply for the credit card.
 * @author Harit Patwa
 */
public class ApplyForCreditCard implements  IAssignCreditCardToUser,IApplyForCreditCard {

  private ICreditCardRecommendationFactory factory;
  private ICreditCardServicesAndRecommendationView view;
  public  ApplyForCreditCard() { }

  @Override
  public IApplyForCreditCard setView(ICreditCardServicesAndRecommendationView view)
  {
    this.view = view;
    return this;
  }

  @Override
  public IApplyForCreditCard setFactory(ICreditCardRecommendationFactory factory) {
    this.factory = factory;
    return this;
  }

  @Override
  public void assignCreditCardToUser() {
    view.showHeader();
    view.listCreditCard();
    view.returnMenu();
    int input = view.getIntegerInput();
    if (input >0 && input <6)
    {
      view.creditCardHeading(view.getCreditCardName(input));
      view.creditCardDetails(view.getCreditCardFees(input), view.getCreditCardDetails(input) );
      int index = input;
      input = view.getIntegerInput();
      if(input == 1) {
        if( factory.getCreditCardDetailsModel().getCreditCardDetails()==null) {
          factory.getCreditCardDetailsModel().insertCreditCardDetails(view.getCreditCardName(index));
          view.congratulationsText(view.getCreditCardName(index));
        } else {
          view.invalidOption();
        }
        view.returnMenu();
        input = view.getIntegerInput();
      }
      while (input != 9) {
        view.showInvalidInput();
        view.returnMenu();
        input = view.getIntegerInput();
      }
    }
  }
}
