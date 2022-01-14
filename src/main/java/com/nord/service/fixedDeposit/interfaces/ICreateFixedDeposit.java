package com.nord.service.fixedDeposit.interfaces;

import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlansModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositMainView;

import java.util.List;

/**
 * Interface to for the create method
 * @author Abdul Mueed Qadri
 */
public interface ICreateFixedDeposit {

  /**
   * After taking the details for the FD a based on the users account balance
   * fixed deposit confirmation is shown to the user
   * @param fdType This is a the type of FD selected of a abstract type.
   * @param fdPlans The list Fixed Deposit Plans that are available in the
   *                Database.
   * @param customerBalance customers account balance used to check if the
   *                        customer is eligible to create the FD.
   * @param view To handle IO from the user
   * @return  the selected FD is returned.
   */
  IUserFixedDepositModel create(IGetFixedDeposit fdType, List<IFixedDepositPlansModel> fdPlans,
                                double customerBalance, IFixedDepositMainView view);
}
