package com.nord.service.fixedDeposit;

import com.nord.common.Constants;
import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlanPersistence;
import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlansModel;
import com.nord.service.fixedDeposit.interfaces.IFixedDeposit;
import com.nord.service.fixedDeposit.interfaces.IFixedDepositFactory;
import com.nord.service.fixedDeposit.interfaces.IGetFixedDeposit;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositMainView;

import java.util.List;

/**
 * Main fixed deposit class handle IO from user
 * @author Abdul Mueed Qadri
 */
public class FixedDeposit implements IFixedDeposit {
    private IFixedDepositMainView view ;
    private List<IFixedDepositPlansModel> fdPlans;
    private IFixedDepositFactory factory;
    private IFixedDepositPlanPersistence persistence;

    public FixedDeposit setView(IFixedDepositMainView view) {
        this.view = view;
        return this;
    }

    public FixedDeposit setFactory(IFixedDepositFactory factory) {
        this.factory = factory;
        return this;
    }

    public FixedDeposit setPersistence(IFixedDepositPlanPersistence persistence){
        this.persistence = persistence;
        return this;
    }

    @Override
    public boolean fixedDepositHomePage() {
        while (true) {
            IGetFixedDeposit fdType = null;
            this.fdPlans = persistence.getFds();
            view.showMenu();
            int input = view.getIntegerInput();
            switch (input) {
                case 1:
                    factory.createGetUserFd(fdPlans).showAllFdsForUser();
                    break;
                case 2:
                    fdType = factory.getFdByPlan(Constants.SHORT_FD);
                    break;
                case 3:
                    fdType = factory.getFdByPlan( Constants.MEDIUM_FD);
                    break;
                case 4:
                    fdType = factory.getFdByPlan( Constants.LONG_FD);
                    break;
                case 5:
                    fdType = factory.getFdByEstimation();
                    break;
                case 6:
                    return true;
                default:
                    view.showInvalidInputNumber();
            }
            if(fdType != null){
                factory.createFixedDeposit().create(fdType, fdPlans,
                        persistence.getCustomerBalance(), view);
            }
        }
    }
}
