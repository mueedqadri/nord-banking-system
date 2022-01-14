package com.nord.view.fixedDeposit.intrefaces;

import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlansModel;
import com.nord.view.interfaces.IUserInterface;

import java.util.List;

/**
 * Interface for fixed deposit plan view
 * @author Abdul Mueed Qadri
 */
public interface IFixedDepositPlanView extends IUserInterface, IFixedDepositView {

  void showNoFdFound();

  void showFixedDepositPlanList(List<IFixedDepositPlansModel> fixedDepositPlans);
}
