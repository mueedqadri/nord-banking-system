package com.nord.persistence.creditCardServicesAndRecommendation.interfaces;

import java.util.Date;

public interface ICreditCardDetails {

  int getUserId();
  void setUserId(int userId);
  int getAmount();
  void setAmount(int amount);
  Date getDate();
  void setDate(Date date);
  int getCcbId();
  void setCcbId(int ccbId);
  void setPaid(boolean paid);

}
