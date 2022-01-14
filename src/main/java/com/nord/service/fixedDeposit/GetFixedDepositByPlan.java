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
 * This class shows multiple options to the user and based on the the
 * selection a Fixed Deposit is selected
 * @author Abdul Mueed Qadri
 */
public class GetFixedDepositByPlan implements IGetFixedDeposit {

    private String selectedTerm;
    private IFixedDepositPlanView view;
    private IUserFixedDepositModel selectedFd;

    public GetFixedDepositByPlan setSelectedFd(IUserFixedDepositModel selectedFd) {
        this.selectedFd = selectedFd;
        return this;
    }

    public GetFixedDepositByPlan setView(IFixedDepositPlanView view) {
        this.view = view;
        return this;
    }

    public GetFixedDepositByPlan setSelectedTerm(String selectedTerm) {
        this.selectedTerm = selectedTerm;
        return this;
    }

    @Override
    public IUserFixedDepositModel getFixedDeposit( List<IFixedDepositPlansModel> fixedDepositPlans) {
        IFixedDepositPlansModel plan = getPlan(fixedDepositPlans);
        if(plan != null){
            selectedFd.setPlanId(plan.getId());
            selectedFd.setAmount(getAmount());
            selectedFd.setWithdrawalDate(getWithdrawalDate(plan));
            selectedFd.setInterest(plan.getInterest());
            double profit = Utils.calculateProfit(selectedFd.getInterest(), selectedFd.getAmount());
            selectedFd.setProfit(profit);
        }else {
            selectedFd = null;
        }
        return selectedFd;
    }

    private IFixedDepositPlansModel getPlan(List<IFixedDepositPlansModel> fixedDepositPlans) {
        int input;
        List<IFixedDepositPlansModel> fdInSelectedTerm = getFdsInSelectedTerm(selectedTerm, fixedDepositPlans);
        view.showFixedDepositPlanList(fdInSelectedTerm);
        do {
            input = view.getIntegerInput();
            if (input <= fdInSelectedTerm.size() && input > 0) {
                IFixedDepositPlansModel fdPlan = fdInSelectedTerm.get(input - 1);
                return fdPlan;
            } else if (input == fdInSelectedTerm.size() + 1) {
                return null;
            } else {
                view.showInvalidInputNumber();
            }
        } while (true);
    }

    private Integer getAmount() {
        view.showAmount();
        return view.getIntegerInput();
    }

    private LocalDate getWithdrawalDate(IFixedDepositPlansModel fdPlan) {
        view.showDateInput();
        do {
            LocalDate enteredDate = view.getDateInputGreaterThanToday();
            long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), enteredDate);
            if (daysBetween < fdPlan.getMinDuration() || daysBetween > fdPlan.getMaxDuration()) {
                view.showChooseCorrectPlan();
            } else {
                return enteredDate;
            }
        } while (true);
    }

    private List<IFixedDepositPlansModel> getFdsInSelectedTerm(String selectedTerm,
                                                                List<IFixedDepositPlansModel> fixedDepositPlans) {
        return fixedDepositPlans.stream()
                .filter((fd) -> fd.getType().equals(selectedTerm))
                .collect(Collectors.toList());
    }
}
