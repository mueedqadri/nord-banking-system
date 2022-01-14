package com.nord.service.fixedDeposit.interfaces;

import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlansModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositsPersistence;

import java.util.List;

/**
 * Interface for Fixed Deposit Factory
 * @author Abdul Mueed Qadri
 */
public interface IFixedDepositFactory {

    IUserFixedDepositsPersistence createUserFdDb();

    IUserFixedDepositModel createUserFdModel();

    IGetFixedDeposit getFdByPlan(String selectedTerm);

    IGetFixedDeposit getFdByEstimation();

    IFixedDepositConfirmation createFdConfirmation(IUserFixedDepositModel selectedFd,
                                                   List<IFixedDepositPlansModel> fixedDepositPlans);

    IGetUserFixedDeposits createGetUserFd(List<IFixedDepositPlansModel> fixedDepositPlans);

    ICreateFixedDeposit createFixedDeposit();
}
