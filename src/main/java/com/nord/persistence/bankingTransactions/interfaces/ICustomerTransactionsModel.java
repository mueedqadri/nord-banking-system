package com.nord.persistence.bankingTransactions.interfaces;

import java.util.Date;

/**
 * This contains abstract methods of CustomerTransactionModel
 * @author Jay Nimeshkumar Patel
 */
public interface ICustomerTransactionsModel {

  void setTransactionId(int transactionId);

  int getUserId();

  void setUserId(int userId);

  String getComment();

  void setComment(String comment);

  double getAmount();

  void setAmount(double amount);

  String getTransactionType();

  void setTransactionType(String transactionType);

  Date getTransactionDate();

  void setTransactionDate(Date transactionDate);
}
