package com.nord.persistence.bankingTransactions.interfaces;

/**
 * This interface is implemented by PayeeDetailsModel class to perform payee related operations
 * @author Rahul Reddy Puchakayala
 */
public interface IPayeeDetailsModel {

  int getUserId();

  void setUserId(int userId);

  int getPayeeId();

  void setPayeeId(int payeeId);

  String getFirstName();

  void setFirstName(String firstName);

  String getLastName();

  void setLastName(String lastName);

  long getAccountNo();

  void setAccountNo(long accountNo);

  String getNickName();

  void setNickName(String nickName);
}
