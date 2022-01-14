package com.nord.persistence.creditScoreLoan.interfaces;

import java.util.Date;

public interface ICreditScoreLoanModel {

  int getAmount();

  void setAmount(int amount);

  Date getDueDate();

  void setDueDate(Date dueDate);

  boolean getPaidStatus();

  void setPaid(boolean isPaid);

  boolean applyForCreditScoreLoan(int amount, String strDate);

  int getCreditScoreLoanCount();

  void setCreditScoreLoanPayStatus(int am1);

}
