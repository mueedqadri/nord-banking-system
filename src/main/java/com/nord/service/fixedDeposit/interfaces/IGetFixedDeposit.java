package com.nord.service.fixedDeposit.interfaces;

import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlansModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;

import java.util.List;

/**
 * Generic Interface to implement the various methods by which a user can
 * select a FD. This interface is implemented by GetFixedDepositByPlan and
 * GetFixedDepositByEstimation
 * @author Abdul Mueed Qadri
 */
public interface IGetFixedDeposit {

    /**
     * Handles to input and out from the user and returns t
     * @param fixedDepositPlans List of FD plans available
     * @return selected FD with interest, amount, withdrawal date, isUpgraded,
     * created date and profit
     */
    IUserFixedDepositModel getFixedDeposit( List<IFixedDepositPlansModel> fixedDepositPlans);
}
