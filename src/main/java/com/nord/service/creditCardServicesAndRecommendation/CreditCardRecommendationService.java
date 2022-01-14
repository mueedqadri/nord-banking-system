package com.nord.service.creditCardServicesAndRecommendation;

import com.nord.service.creditCardServicesAndRecommendation.interfaces.ICreditCardRecommendationFactory;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.ICreditCardRecommendationService;
import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardServicesAndRecommendationView;
import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardTypesView;

/**
 * This class is representing the main feature of Credit Card services and recommendation,
 * It has various methods for setting builder and Homepage method.
 * @author Harit Patwa
 */

public class CreditCardRecommendationService implements ICreditCardRecommendationService {

  private ICreditCardServicesAndRecommendationView view;
  private ICreditCardTypesView typesView;
  private ICreditCardRecommendationFactory factory;

  public CreditCardRecommendationService() { }

  public CreditCardRecommendationService setView(ICreditCardServicesAndRecommendationView creditServiceView) {
    this.view = creditServiceView;
    return this;
  }

  public CreditCardRecommendationService setTypeView(ICreditCardTypesView typesView) {
    this.typesView = typesView;
    return this;
  }

  public CreditCardRecommendationService setFactory(ICreditCardRecommendationFactory factory) {
    this.factory = factory;
    return this;
  }

  @Override
  public void creditCardRecommendationHomepage(){
    while (true) {
      view.showMenu();
      view.returnMenu();
      int input = view.getIntegerInput();
      switch (input) {
        case 1:
          factory.payCreditCardBill()
                  .setView(view)
                  .setFactory(factory)
                  .payCreditCardBill();
          break;
        case 2:
          factory.upgradeCreditCard()
                  .setFactory(factory)
                  .setView(view)
                  .assignCreditCardToUser();
          break;
        case 3:
          factory.applyForCreditCard()
                  .setFactory(factory)
                  .setView(view)
                  .assignCreditCardToUser();
         break;
        case 4:
          factory.recommendCreditCard()
                  .setView(view)
                  .setCreditCardTypeView(typesView)
                  .recommendCreditCard();
          break;
        case 9:
          return;
        default:
          view.showInvalidInput();
      }
    }
  }
}
