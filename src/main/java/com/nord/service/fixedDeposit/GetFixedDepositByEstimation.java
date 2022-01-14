package com.nord.service.fixedDeposit;

import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlansModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.service.fixedDeposit.interfaces.IGetFixedDeposit;
import com.nord.common.Utils;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositPlanView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Selects the Fixed Deposit by estimation based on user input
 * @author Abdul Mueed Qadri
 */
public class GetFixedDepositByEstimation implements IGetFixedDeposit {

    private IFixedDepositPlanView view;
    private IUserFixedDepositModel selectedFd;

    public GetFixedDepositByEstimation setSelectedFd(IUserFixedDepositModel selectedFd) {
        this.selectedFd = selectedFd;
        return this;
    }

    public GetFixedDepositByEstimation setView(IFixedDepositPlanView view) {
        this.view = view;
        return this;
    }

    @Override
    public IUserFixedDepositModel getFixedDeposit( List<IFixedDepositPlansModel> fixedDepositPlans) {
        view.showAmount();
        selectedFd.setAmount(view.getIntegerInput());
        view.showDateInput();
        selectedFd.setWithdrawalDate(view.getDateInputGreaterThanToday());
        IFixedDepositPlansModel plan = estimateFdPlan(selectedFd, fixedDepositPlans);
        if(plan != null ){
            selectedFd.setPlanId(plan.getId());
            selectedFd.setInterest(plan.getInterest());
            double profit = Utils.calculateProfit(selectedFd.getInterest(), selectedFd.getAmount());
            selectedFd.setProfit(profit);
            return selectedFd;
        }
        return null;
    }

    private IFixedDepositPlansModel getPlan(int noOfDays, List<IFixedDepositPlansModel> fixedDepositPlans) {
        try {
            return fixedDepositPlans.stream()
                    .filter((fd) -> noOfDays > fd.getMinDuration() && noOfDays < fd.getMaxDuration() )
                    .collect(Collectors.toList())
                    .get(0);
        }catch (Exception e){
            return null;
        }
    }

    private IFixedDepositPlansModel estimateFdPlan(IUserFixedDepositModel selectedFd, List<IFixedDepositPlansModel> fixedDepositPlans){
        int daysBetween = (int) ChronoUnit.DAYS.between(LocalDate.now(), selectedFd.getWithdrawalDate());
        IFixedDepositPlansModel estimatedFd = getPlan(daysBetween, fixedDepositPlans);
        if(estimatedFd != null){
             return estimatedFd;
        }
        view.showNoFdFound();
        return null;
    }
}
