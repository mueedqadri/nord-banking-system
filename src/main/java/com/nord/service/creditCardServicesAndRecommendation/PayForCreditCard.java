package com.nord.service.creditCardServicesAndRecommendation;

import com.nord.persistence.creditScoreLoan.CustomerBalanceManagement;
import com.nord.persistence.creditCardServicesAndRecommendation.interfaces.ICreditCardDetails;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.ICreditCardRecommendationFactory;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.IPayForCreditCard;
import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardServicesAndRecommendationView;

import java.util.List;

/**
 * This class handles paying feature of the credit card bill
 * handles the business logic of bill payment
 * @author Harit Patwa
 */
public class PayForCreditCard implements IPayForCreditCard {
  private ICreditCardServicesAndRecommendationView view;
  private ICreditCardRecommendationFactory factory;

  @Override
  public IPayForCreditCard setView(ICreditCardServicesAndRecommendationView view) {
    this.view=view;
    return this;
  }

  @Override
  public IPayForCreditCard setFactory(ICreditCardRecommendationFactory factory) {
    this.factory=factory;
    return this;
  }

  @Override
  public void payCreditCardBill() {
    view.showHeader();
    List<ICreditCardDetails> unpaidBills = factory.getCreditCardBills().getBills();
    view.showUnpaidBills(unpaidBills);
    int input = view.getIntegerInput();
    if(input<=unpaidBills.size() && input>0)
    {
      factory.getCreditCardBills().payBill(unpaidBills.get(input-1).getCcbId());
      int customerBalance = new CustomerBalanceManagement().getCustomerBalance();
      if(customerBalance>unpaidBills.get(input-1).getAmount()) {
        new CustomerBalanceManagement().setCustomerBalance(unpaidBills.get(input - 1).getAmount());
        view.congratulationsForBillPay();
      }else {
        view.notEnoughBalance();
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
