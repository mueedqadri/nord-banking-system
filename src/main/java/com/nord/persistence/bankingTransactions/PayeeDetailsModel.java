package com.nord.persistence.bankingTransactions;

import com.nord.persistence.bankingTransactions.interfaces.IPayeeDetailsModel;

/**
 * This is a POJO class representing the payee_details table with all its attributes
 * @author Rahul Reddy Puchakayala
 */
public class PayeeDetailsModel implements IPayeeDetailsModel {

  private int payeeId;
  private int userId;
  private String firstName;
  private String lastName;
  private long accountNo;
  private String nickName;

  @Override
  public int getUserId() {
    return userId;
  }

  @Override
  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public int getPayeeId() {
    return payeeId;
  }

  @Override
  public void setPayeeId(int payeeId) {
    this.payeeId = payeeId;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public long getAccountNo() {
    return accountNo;
  }

  @Override
  public void setAccountNo(long accountNo) {
    this.accountNo = accountNo;
  }

  @Override
  public String getNickName() {
    return nickName;
  }

  @Override
  public void setNickName(String nickName) {
    this.nickName = nickName;
  }
}
