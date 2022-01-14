package com.nord.service.fixedDeposit;

import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlansModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositsPersistence;
import com.nord.service.fixedDeposit.interfaces.IGetUserFixedDeposits;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositUserPlansView;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * shows list of already created Fds to the user. After selection user can
 * see the details and choose to withdraw the FD
 * @author Abdul Mueed Qadri
 */
public class GetUserFixedDeposits implements IGetUserFixedDeposits {
    private IFixedDepositUserPlansView view;
    private List<IUserFixedDepositModel> userFds;
    private List<IFixedDepositPlansModel> fdPlans;
    private IUserFixedDepositsPersistence db;

    public GetUserFixedDeposits setDb(IUserFixedDepositsPersistence userFixedDeposits){
        this.db = userFixedDeposits;
        return this;
    }

    public GetUserFixedDeposits setView(IFixedDepositUserPlansView view) {
        this.view = view;
        return this;
    }

    public GetUserFixedDeposits setFdPlans(List<IFixedDepositPlansModel> fdPlans) {
        this.fdPlans = fdPlans;
        return this;
    }

    @Override
    public List<IUserFixedDepositModel> showAllFdsForUser() {
        do {
            userFds = db.getAllFdsForUser();
            view.showAllUserFds(userFds);
            int input = view.getIntegerInput();
            if (input <= userFds.size() && input > 0) {
                int fdId = userFds.get(input - 1).getFdId();
                handleUserFd(fdId);
                return userFds;
            } else if (input == userFds.size() + 1) {
                return userFds;
            } else {
                view.showInvalidInputNumber();
            }
        } while (true);
    }

    private void handleUserFd(int fdId) {
        int input;
        IUserFixedDepositModel selectedFd = getSelectedFdById(fdId);
        IFixedDepositPlansModel fdPlan = getSelectedFdPlanById(selectedFd.getPlanId(), fdPlans);
        selectedFd.setInterest(fdPlan.getInterest());
        do {
            view.showUserFdDetails(selectedFd);
            input = view.getIntegerInput();
            if(input == 1){
                withdrawFixedDeposit(selectedFd);
                return;
            }
        } while (input != 0);
    }

    private void withdrawFixedDeposit(IUserFixedDepositModel selectedFd){
        if(selectedFd.getWithdrawalDate().isAfter(LocalDate.now())){
            while(true){
                view.showImmatureWithdrawalWarning();
                int input = view.getIntegerInput();
                if(input == 1){
                    handleSuccessfulWithdrawal(selectedFd.getFdId(), selectedFd.getAmount());
                    return;
                } else if(input == 0){
                    return;
                }
            }
        }
        else {
            double amount = selectedFd.getAmount() + selectedFd.getProfit();
            handleSuccessfulWithdrawal(selectedFd.getFdId(), amount);
        }
    }

    public void handleSuccessfulWithdrawal(int fdId, double amount){
        db.withdrawFd(fdId, amount);
        view.showSuccessfulWithdrawal(amount);
        while(view.getIntegerInput() != 0);
    }

    private IFixedDepositPlansModel getSelectedFdPlanById(int id, List<IFixedDepositPlansModel> fixedDepositPlans) {
        return fixedDepositPlans.stream()
                .filter((fd) -> fd.getId() == id)
                .collect(Collectors.toList())
                .get(0);
    }

    private IUserFixedDepositModel getSelectedFdById(int id) {
        return userFds.stream()
                .filter(fd -> fd.getFdId() == id)
                .collect(Collectors.toList()).get(0);
    }
}
