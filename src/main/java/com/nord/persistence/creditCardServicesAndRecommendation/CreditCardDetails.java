package com.nord.persistence.creditCardServicesAndRecommendation;

import com.nord.persistence.creditCardServicesAndRecommendation.interfaces.ICreditCardDetails;

import java.util.Date;

/**
 * This class stores credit card details object
 * @author Harit Patwa
 */

public class CreditCardDetails implements ICreditCardDetails {

  private int userId;
  private int amount;
  private boolean isPaid;
  private Date date;
  private int ccbId;

  public CreditCardDetails() { }

  @Override
  public int getUserId() {
    return userId;
  }

  @Override
  public int getAmount() {
    return amount;
  }

  @Override
  public Date getDate() {
    return date;
  }

  @Override
  public int getCcbId() {
    return ccbId;
  }

  @Override
  public void setCcbId(int ccbId) {
    this.ccbId =ccbId;
  }

  @Override
  public void setAmount(int amount) {
this.amount=amount;
  }

  @Override
  public void setPaid(boolean paid) {
  this.isPaid=paid;
  }

  @Override
  public void setUserId(int userId) {
  this.userId = userId;
  }

  @Override
  public void setDate(Date date) {
    this.date=date;
  }
}
