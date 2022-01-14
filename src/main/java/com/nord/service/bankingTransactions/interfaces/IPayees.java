package com.nord.service.bankingTransactions.interfaces;

import com.nord.persistence.bankingTransactions.interfaces.IPayeeDetailsModel;
import com.nord.view.bankingTransactions.interfaces.IPayeesView;

import java.util.List;

/**
 * This interface is implemented by AbstactPayee class
 * @author Rahul Reddy Puchakayala
 */
public interface IPayees {

  /**
   * provides the details of payee which the user selects
   * @param payeesView payee view object to retrieve the list of user's payee
   * @param payeeDetailsList list of payees
   * @return payee details model object of selected payee
   */
  IPayeeDetailsModel selectPayee(IPayeesView payeesView, List<IPayeeDetailsModel> payeeDetailsList);
}
