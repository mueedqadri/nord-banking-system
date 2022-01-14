package com.nord.service.bankingTransactions;

import com.nord.persistence.bankingTransactions.CustomerPayees;
import com.nord.persistence.bankingTransactions.MakeCustomerTransactions;
import com.nord.persistence.bankingTransactions.interfaces.ICustomerPayees;
import com.nord.persistence.bankingTransactions.interfaces.IMakeCustomerTransactions;
import com.nord.service.bankingTransactions.interfaces.IPayeeOperations;
import com.nord.service.bankingTransactions.interfaces.ITransaction;
import com.nord.service.bankingTransactions.interfaces.ITransactionsFactory;
import com.nord.view.bankingTransactions.PayeesView;
import com.nord.view.bankingTransactions.TransactionsView;
import com.nord.view.bankingTransactions.interfaces.IPayeesView;
import com.nord.view.bankingTransactions.interfaces.ITransactionsView;

/**
 * This class represents factory methods for Banking Transaction Feature
 * @author Jay Nimeshkumar Patel, Rahul Reddy Puchakayala
 */
public class TransactionsFactory implements ITransactionsFactory {

  @Override
  public ICustomerPayees getPayeesForCustomer() {
    return new CustomerPayees();
  }

  @Override
  public IMakeCustomerTransactions makeCustomerTransactions() {
    return new MakeCustomerTransactions();
  }

  @Override
  public IPayeeOperations createPayeeObject() {
    return new PayeeOperations().setFactory(this).setPayeeView(createPayeeView());
  }

  private IPayeesView createPayeeView() {
    return new PayeesView();
  }

  @Override
  public ITransaction createTransactionsObject() {
    return new TransactionService().setFactory(this).setTransactionsView(createTransactionsView()).setPayeesView(createPayeeView());
  }

  private ITransactionsView createTransactionsView() {
    return new TransactionsView();
  }

}
