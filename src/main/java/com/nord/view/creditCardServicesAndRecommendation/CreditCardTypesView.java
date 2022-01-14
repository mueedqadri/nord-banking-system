package com.nord.view.creditCardServicesAndRecommendation;

import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardTypesView;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the presentation layer of Credit Card and Recommendation Service View
 * @author Harit Patwa
 */

public class CreditCardTypesView implements ICreditCardTypesView {
  Map<Integer, String> creditCardTypes;
  Map<Integer, String> creditCardDetails;
  Map<Integer, Integer> creditCardFees;
  public CreditCardTypesView()
  {
    creditCardTypes = new HashMap<>();
    creditCardDetails = new HashMap<>();
    creditCardFees = new HashMap<>();
    setCreditCardNames();
    setCreditCardDetail();
    setCreditCardFees();
  }
  public void setCreditCardNames()
  {
    creditCardTypes.put(1,"MoneyBack Credit Card");
    creditCardTypes.put(2,"Gold Credit Card");
    creditCardTypes.put(3,"Millennia Credit Card");
    creditCardTypes.put(4,"Titanium Credit Card");
    creditCardTypes.put(5,"Platinum Credit Card");
  }
  public void setCreditCardDetail()
  {
    creditCardDetails.put(1,"Accelerated Moneyback rewards for students. \n" +
    "Interest rates: 19.99% purchases / \n" +
            "                22.99% cash advances");
    creditCardDetails.put(2,"Complimentary Ola Select | cure.fit Live | BookMyShow \n" +
            "Interest rates: 19.99% purchases / \n" +
            "                22.99% cash advances");
    creditCardDetails.put(3,"1% Cash back on all eligible gas station, grocery store, drug " +
            "store purchases and recurring payments. \n" +
            "Interest rates: 19.99% purchases / \n" +
            "                22.99% cash advances");
    creditCardDetails.put(4,"4% groceries and recurring payments, 2% Cash back on all eligible " +
            "gas station, grocery store, drug store purchases and recurring payments. \n" +
            "Interest rates: 19.99% purchases / \n" +
            "                22.99% cash advances");
    creditCardDetails.put(5,"You will not pay 2.5% foreign transaction fees on any foreign currency" +
            " purchases, including online shopping and when travelling abroad \n" +
            "Interest rates: 19.99% purchases / \n" +
            "                22.99% cash advances");
  }
  public void setCreditCardFees() {
    creditCardFees.put(1,0);
    creditCardFees.put(2,39);
    creditCardFees.put(3,79);
    creditCardFees.put(4,120);
    creditCardFees.put(5,399);
  }
  public String getCreditCardName(int index)
  {
    return creditCardTypes.get(index);
  }

  @Override
  public String getCreditCardDetails(int index)
  {
    return creditCardDetails.get(index);
  }
  @Override
  public int getCreditCardFees(int index)
  {
    return creditCardFees.get(index);
  }
}
