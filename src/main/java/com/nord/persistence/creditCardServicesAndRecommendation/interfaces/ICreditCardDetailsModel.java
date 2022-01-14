package com.nord.persistence.creditCardServicesAndRecommendation.interfaces;

public interface ICreditCardDetailsModel {
  /**
   * Returns the credit card name for the particular user
   * @return credit card name
   */
String getCreditCardDetails();
  /**
   * sets the Credit Card Details
   * @return true if success
   */
boolean setCreditCardDetails(String creditCardName);
  /**
   * Inserts the credit Card details
   * @return true if success
   */
boolean insertCreditCardDetails(String creditCardName);

}
