package com.nord.view.fixedDeposit.intrefaces;

import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.view.interfaces.IUserInterface;

/**
 * Interface for Fixed Deposit confirmation view
 * @author Abdul Mueed Qadri
 */
public interface IFixedDepositConfirmationView extends IUserInterface, IFixedDepositView {

  void showFdConfirmation(IUserFixedDepositModel fd);

  void showSuggestion(String suggestion);

  void showFdAddedSuccessful();
}
