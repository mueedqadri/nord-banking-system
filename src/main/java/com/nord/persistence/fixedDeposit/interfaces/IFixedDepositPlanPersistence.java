package com.nord.persistence.fixedDeposit.interfaces;

import com.nord.persistence.bankingTransactions.interfaces.IBanking;

import java.util.List;

/**
 * Interface implemented by FixedDepositPlanPersistence
 * @author Abdul Mueed Qadri
 */
public interface IFixedDepositPlanPersistence extends IBanking {

    /**
     * Gets the list of FD plans from the Database
     * @return list of FDs
     */
    List<IFixedDepositPlansModel> getFds();
}
