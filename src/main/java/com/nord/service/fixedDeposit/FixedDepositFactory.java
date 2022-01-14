package com.nord.service.fixedDeposit;

import com.nord.persistence.fixedDeposit.FixedDepositPlanPersistence;
import com.nord.persistence.fixedDeposit.UserFixedDepositModel;
import com.nord.persistence.fixedDeposit.UserFixedDepositsPersistence;
import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlanPersistence;
import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlansModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositsPersistence;
import com.nord.service.fixedDeposit.interfaces.*;
import com.nord.view.fixedDeposit.FixedDepositConfirmationView;
import com.nord.view.fixedDeposit.FixedDepositPlanView;
import com.nord.view.fixedDeposit.FixedDepositUserPlansView;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositConfirmationView;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositPlanView;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositUserPlansView;

import java.util.List;

/**
 * Factory class for Fixed Deposits
 * @author Abdul Mueed Qadri
 */
public class FixedDepositFactory implements IFixedDepositFactory {
    @Override
    public IUserFixedDepositsPersistence createUserFdDb(){
        return new UserFixedDepositsPersistence();
    }

    @Override
    public IUserFixedDepositModel createUserFdModel(){
        return new UserFixedDepositModel();
    }

    @Override
    public IGetFixedDeposit getFdByPlan(String selectedTerm){
        return new GetFixedDepositByPlan()
                .setView(createPlanView())
                .setSelectedFd(createUserFdModel())
                .setSelectedTerm(selectedTerm);
    }

    @Override
    public IGetFixedDeposit getFdByEstimation(){
        return new GetFixedDepositByEstimation()
                .setSelectedFd(createUserFdModel())
                .setView(createPlanView());
    }

    @Override
    public IFixedDepositConfirmation createFdConfirmation(IUserFixedDepositModel selectedFd,
                                                          List<IFixedDepositPlansModel> fixedDepositPlans){
       return new FixedDepositConfirmation()
                .setView(createFdConfirmationView())
                .setUserPersistence(createUserFdDb())
                .setFixedDepositPlans(fixedDepositPlans)
                .setSelectedFd(selectedFd)
                .setUpgradeableFd(createUserFdModel());
    }

    @Override
    public IGetUserFixedDeposits createGetUserFd(List<IFixedDepositPlansModel> fixedDepositPlans){
        return new GetUserFixedDeposits()
                .setDb(createUserFdDb())
                .setFdPlans(fixedDepositPlans)
                .setView(createUserPlansView());
    }

    @Override
    public ICreateFixedDeposit createFixedDeposit(){
       return new CreateFixedDeposit()
                .setFactory(this);
    }

    private IFixedDepositPlanView createPlanView (){
        return new FixedDepositPlanView();
    }

    private IFixedDepositUserPlansView createUserPlansView(){
        return new FixedDepositUserPlansView();
    }

    private IFixedDepositConfirmationView createFdConfirmationView(){
        return new FixedDepositConfirmationView();
    }
}
