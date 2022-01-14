package com.nord.persistence.fixedDeposit.interfaces;

import java.util.List;

/**
 * Interface for handling the user FDs
 * @author Abdul Mueed Qadri
 */
public interface IUserFixedDepositsPersistence {

    /**
     * Adds the FD selected by the user to the database
     * @param fd selected FD
     * @return true if the user is created successfully
     */
    boolean addFdToUser(IUserFixedDepositModel fd);

    /**
     * Fetches the User FDs from the Database
     * @return list of user FDs
     */
    List<IUserFixedDepositModel> getAllFdsForUser();

    /**
     * Removes the FD from the database and adds the balance to the user account
     * @param fdId FD that is to be closed
     * @param amount Amount to be credited to the account balance
     * @return true is operation successful
     */
    boolean withdrawFd(int fdId, double amount);
}
