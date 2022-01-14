package com.nord.service.bankingTransactions.interfaces;

import com.nord.persistence.bankingTransactions.interfaces.ICustomerPayees;
import com.nord.persistence.bankingTransactions.interfaces.IMakeCustomerTransactions;

/**
 * This class represents factory methods for Banking Transaction Feature
 * @author Jay Nimeshkumar Patel, Rahul Reddy Puchakayala
 */
public interface ITransactionsFactory {

  ICustomerPayees getPayeesForCustomer();

  IMakeCustomerTransactions makeCustomerTransactions();

  IPayeeOperations createPayeeObject();

  ITransaction createTransactionsObject();
}
