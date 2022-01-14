package com.nord.service.fixedDeposit.interfaces;

import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;

import java.util.List;

/**
 * Interface implemented by GetUserFixedDeposits
 * @author Abdul Mueed Qadri
 */
public interface IGetUserFixedDeposits {

    /**
     * Gets the list of FDs for a user from the persistence and calls the
     * view to display it to the user
     * @return list of userFds for a user
     */
    List<IUserFixedDepositModel> showAllFdsForUser();
}
