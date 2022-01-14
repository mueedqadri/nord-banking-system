package com.nord.service.bankingTransactions;

import com.nord.persistence.bankingTransactions.interfaces.IPayeeDetailsModel;
import com.nord.service.bankingTransactions.interfaces.IPayees;
import com.nord.view.bankingTransactions.interfaces.IPayeesView;

import java.util.List;

/**
 * Abstract class provides the details of payee which the customer selects
 * @author Rahul Reddy Puchakayala
 */
public abstract class AbstractPayee implements IPayees {

  @Override
  public IPayeeDetailsModel selectPayee(IPayeesView payeesView, List<IPayeeDetailsModel> payeeDetailsList) {
    int payeeIndex;
    IPayeeDetailsModel selectedPayee;
    if (payeeDetailsList.isEmpty()) {
      payeesView.displayEmptyPayeeMessage();
      return null;
    }
    int index = 1;
    for (IPayeeDetailsModel payee : payeeDetailsList) {
      payeesView.showPayeesList(index++, payee.getFirstName());
    }
    do {
      payeeIndex = payeesView.selectPayee();
      if (payeeIndex <= payeeDetailsList.size() && payeeIndex > 0) {
        selectedPayee = payeeDetailsList.get(payeeIndex - 1);
        payeesView.listOfPayeesData(selectedPayee);
        return selectedPayee;
      } else if (payeeIndex == 0) {
        return null;
      }
    } while (true);
  }
}
