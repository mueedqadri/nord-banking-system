package com.nord.service.creditScoreLoan;

import com.nord.persistence.creditScoreLoan.interfaces.ICreditScoreLoanModel;
import com.nord.service.creditScoreLoan.interfaces.ICreditScoreLoanFactory;
import com.nord.service.creditScoreLoan.interfaces.IPayCreditLoan;
import com.nord.view.creditScoreLoan.interfaces.ICreditScoreLoanView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class handles paying mechanism of credit score loan
 * @author Harit Patwa
 */
public class PayCreditLoan implements IPayCreditLoan {

  private int amount;
  private int balance;
  private int crsScore;
  private Date date;
  private int userID;
  private ICreditScoreLoanView view;
  private ICreditScoreLoanFactory factory;

  @Override
  public void payCreditLoanByGivenLoans(List<ICreditScoreLoanModel> userLoans) {

    List<ICreditScoreLoanModel> unpaidLoans = getUnpaidLoans(userLoans);
    if(unpaidLoans.isEmpty()) {
      view.noPendingLoan();
      view.returnMenu();
      int input = view.getIntegerInput();
      while (input != 1) {
        view.showInvalidInput();
        view.returnMenu();
        input = view.getIntegerInput();
      }
    } else {
      view.selectLoanLine();
      view.payLoan(unpaidLoans);
      int input = view.getIntegerInput();
      if(input == (unpaidLoans.size() + 1)) {
        return;
      } else if(input <= unpaidLoans.size()) {
        int amount = unpaidLoans.get(input - 1).getAmount();
        Date strDate = unpaidLoans.get(input - 1).getDueDate();
        if(payCreditLoanByDate(amount, strDate)) {
          view.loanPaymentSuccess();
        } else {
          view.noBalance();
        }

        view.returnMenu((getPayLoanCount(userLoans) + 1));
        input = view.getIntegerInput();
        while (input != (getPayLoanCount(userLoans) + 1)) {
          view.showInvalidInput();
          view.returnMenu();
          input = view.getIntegerInput();
        }
      } else {
        view.showInvalidInput();
        view.returnMenu();
        while (input != 1) {


          input = view.getIntegerInput();
          if(input != 1) {
            view.showInvalidInput();
            view.returnMenu();
          }

        }
      }
    }
  }

  public boolean payCreditLoanByDate(int amount, Date date) {
    this.amount = amount;
    this.date = date;
    balance = factory.getCustomerBalanceManagement().getCustomerBalance();
    if(balance > amount) {
      Date today = Calendar.getInstance().getTime();
      if(today.compareTo(date) > 0) {
        this.crsScore = factory.getCreditScoreModel().getCrsScore();
        crsScore = crsScore - 10;
        factory.getCustomerBalanceManagement().setCustomerBalance(balance - amount);
        factory.getCreditScoreModel().setCrsScore(crsScore);
        factory.getCreditScoreLoanModel().setCreditScoreLoanPayStatus(amount);
        return true;
      } else if(today.compareTo(date) < 0) {
        this.crsScore = factory.getCreditScoreModel().getCrsScore();
        crsScore = crsScore + 5;
        factory.getCustomerBalanceManagement().setCustomerBalance(balance - amount);
        factory.getCreditScoreModel().setCrsScore(crsScore);
        factory.getCreditScoreLoanModel().setCreditScoreLoanPayStatus(amount);
        return true;
      } else {
        this.crsScore = factory.getCreditScoreModel().getCrsScore();
        crsScore = crsScore + 4;
        factory.getCustomerBalanceManagement().setCustomerBalance(balance - amount);
        factory.getCreditScoreModel().setCrsScore(crsScore);
        factory.getCreditScoreLoanModel().setCreditScoreLoanPayStatus(amount);
        return true;
      }
    } else
      return false;
  }

  public int getPayLoanCount(List<ICreditScoreLoanModel> creditScoreLoanModels) {
    int count = 0;
    if(! creditScoreLoanModels.isEmpty()) {

      for(ICreditScoreLoanModel cslm: creditScoreLoanModels) {
        if(! cslm.getPaidStatus()) {
          count++;
        }
      }

    }
    return count;
  }
  @Override
  public IPayCreditLoan setView(ICreditScoreLoanView view) {
    this.view = view;
    return this;
  }
  @Override
  public IPayCreditLoan setFactory(ICreditScoreLoanFactory factory) {
    this.factory = factory;
    return this;
  }
  public List<ICreditScoreLoanModel> getUnpaidLoans(List<ICreditScoreLoanModel> creditScoreLoansEmpty) {
    List<ICreditScoreLoanModel> unpaid = new ArrayList<>();
    if(! creditScoreLoansEmpty.isEmpty()) {

      for(ICreditScoreLoanModel cslm: creditScoreLoansEmpty) {
        if(! cslm.getPaidStatus()) {
          unpaid.add(cslm);
        }
      }

    }
    return unpaid;
  }
}
