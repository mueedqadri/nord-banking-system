package com.nord.persistence.bankingTransactions;

import com.nord.persistence.bankingTransactions.interfaces.ICustomerTransactionsModel;
import java.util.Date;

/**
 * This is a Model class representing customer transaction entries
 * @author Jay Nimeshkumar Patel
 */
public class CustomerTransactionsModel implements ICustomerTransactionsModel {

  private int transactionId;
  private int userId;
  private String transactionType;
  private String comment;
  private double amount;
  private Date transactionDate;

  @Override
  public void setTransactionId(int transactionId) {
    this.transactionId = transactionId;
  }

  @Override
  public int getUserId() {
    return userId;
  }

  @Override
  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public String getComment() {
    return comment;
  }

  @Override
  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public double getAmount() {
    return amount;
  }

  @Override
  public void setAmount(double amount) {
    this.amount = amount;
  }

  @Override
  public String getTransactionType() {
    return transactionType;
  }

  @Override
  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  @Override
  public Date getTransactionDate() {
    return transactionDate;
  }

  @Override
  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }

}
