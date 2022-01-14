package com.nord.view.fixedDeposit.intrefaces;

import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.view.interfaces.IUserInterface;

/**
 * Generic interface for fixed deposit view
 * @author Abdul Mueed Qadri
 */
public interface IFixedDepositView extends IUserInterface {

  void showHeader();

  void showDateInput();

  void showChooseCorrectPlan();

  void showFdSummary(IUserFixedDepositModel fd);

  void notEnoughBalance();

  void showAmount();
}
