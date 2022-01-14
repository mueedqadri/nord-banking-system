package com.nord.view.fixedDeposit.intrefaces;

import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.view.interfaces.IUserInterface;

import java.util.List;

/**
 * Interface for fixed deposit plan view
 * @author Abdul Mueed Qadri
 */
public interface IFixedDepositUserPlansView extends IUserInterface, IFixedDepositView {

  void showAllUserFds(List<IUserFixedDepositModel> userFixedDepositModels);

  void showUserFdDetails(IUserFixedDepositModel selectedFd);

  void showSuccessfulWithdrawal(double amount);

  void showImmatureWithdrawalWarning();
}
