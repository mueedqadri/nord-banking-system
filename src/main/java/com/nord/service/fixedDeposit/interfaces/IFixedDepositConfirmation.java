package com.nord.service.fixedDeposit.interfaces;

/**
 * Interface for FixedDepositConfirmation
 * @author Abdul Mueed Qadri
 */
public interface IFixedDepositConfirmation {

    /**
     * Confirms the user FD and displays a suggestion if an upgrade is
     * available
     * @return true on successful creation
     */
    boolean fdConfirmation();
}
