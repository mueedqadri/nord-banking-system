package com.nord.service.fixedDeposit;

import com.nord.service.fixedDeposit.interfaces.IFixedDepositConfirmation;
import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlansModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositsPersistence;
import com.nord.common.Utils;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositConfirmationView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Shows confirmation and suggestion for fixed Deposit
 * @author Abdul Mueed Qadri
 */
public class FixedDepositConfirmation  implements IFixedDepositConfirmation {
    private IFixedDepositConfirmationView view;
    private IUserFixedDepositModel selectedFd;
    private IUserFixedDepositsPersistence persistence;
    private IUserFixedDepositModel upgradeableFd;
    private List<IFixedDepositPlansModel> fixedDepositPlans;
    private double SUGGESTION_TRIGGER = .25;

    public FixedDepositConfirmation setView(IFixedDepositConfirmationView view) {
        this.view = view;
        return this;
    }

    public FixedDepositConfirmation setUserPersistence(IUserFixedDepositsPersistence userFixedDeposits){
        this.persistence = userFixedDeposits;
        return this;
    }

    public FixedDepositConfirmation setUpgradeableFd(IUserFixedDepositModel upgradeableFd) {
        this.upgradeableFd = upgradeableFd;
        return this;
    }

    public FixedDepositConfirmation setSelectedFd(IUserFixedDepositModel selectedFd) {
        this.selectedFd = selectedFd;
        return this;
    }

    public FixedDepositConfirmation setFixedDepositPlans(List<IFixedDepositPlansModel> fixedDepositPlans) {
        this.fixedDepositPlans = fixedDepositPlans;
        return this;
    }

    @Override
    public boolean fdConfirmation() {
        int input;
        view.showFdConfirmation(selectedFd);
        boolean isFdUpgradable = isFdUpgradeable();
        if (isFdUpgradable) {
            view.showSuggestion(getSuggestionString());
        }
        do {
            input = view.getIntegerInput();
            switch (input) {
                case 1:
                    persistence.addFdToUser(selectedFd);
                    view.showFdAddedSuccessful();
                    return true;
                case 2:
                    return false;
                case 3:
                    if (isFdUpgradable) {
                        upgradePlan();
                        return true;
                    }
                default:
                    view.showInvalidInputNumber();
            }
        } while (true);
    }

    private void upgradePlan() {
        selectedFd.setPlanId(upgradeableFd.getPlanId());
        selectedFd.setWithdrawalDate(upgradeableFd.getWithdrawalDate());
        selectedFd.setProfit(upgradeableFd.getProfit());
        selectedFd.setInterest(upgradeableFd.getInterest());
        selectedFd.setIsUpgraded(true);
        fdConfirmation();
    }

    private void setUpgradeableFd(IFixedDepositPlansModel fd, int daysBetween){
        double profit = Utils.calculateProfit(fd.getInterest(), this.selectedFd.getAmount());
        int noOfDaysToAddForUpgrade = fd.getMinDuration() - daysBetween;
        upgradeableFd.setPlanId(fd.getId());
        upgradeableFd.setInterest(fd.getInterest());
        upgradeableFd.setProfit(profit);
        upgradeableFd.setWithdrawalDate(selectedFd.getWithdrawalDate().plusDays(noOfDaysToAddForUpgrade));
    }

    private boolean isFdUpgradeable() {
        int daysBetween = (int) ChronoUnit.DAYS.between(LocalDate.now(), selectedFd.getWithdrawalDate());
        int suggestionTrigger = (int) (daysBetween * SUGGESTION_TRIGGER);
        for (IFixedDepositPlansModel fd : fixedDepositPlans) {
            boolean planNotCurrentPlan = fd.getId() != selectedFd.getPlanId();
            boolean selectOnlyUpgradeFds = daysBetween < fd.getMinDuration();
            boolean shouldBeTriggered = daysBetween + suggestionTrigger >= fd.getMinDuration();
            if ( planNotCurrentPlan && selectOnlyUpgradeFds && shouldBeTriggered) {
                setUpgradeableFd(fd, daysBetween);
                return true;
            }
        }
        return false;
    }

    private String getSuggestionString(){
        int daysForUpgrade = (int ) ChronoUnit.DAYS.between(selectedFd.getWithdrawalDate(),
                upgradeableFd.getWithdrawalDate());
        String daysString = Utils.daysConvertToString(daysForUpgrade);
        StringBuilder sb = new StringBuilder();
        long rewardPoints = Utils.calculateRewardPointFd(selectedFd);
        sb.append("If you add " + daysString + "you can enjoy " );
        sb.append("interest of "+ upgradeableFd.getInterest() + "% and ");
        sb.append("your net profit will be $" + upgradeableFd.getProfit());
        sb.append("\n Also you will receive "+rewardPoints+"  Reward Points");
        return sb.toString();
    }
}
