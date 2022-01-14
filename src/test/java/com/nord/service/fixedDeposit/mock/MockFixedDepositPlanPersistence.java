package com.nord.service.fixedDeposit.mock;

import com.nord.persistence.fixedDeposit.FixedDepositPlansModel;
import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlanPersistence;
import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlansModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock Persistence created to mock the persistence handling the fixed
 * deposit plans
 * @author Abdul Mueed Qadri
 */
public class MockFixedDepositPlanPersistence implements IFixedDepositPlanPersistence {

  private int MOCK_BALANCE = 50000;

  @Override
  public List<IFixedDepositPlansModel> getFds() {
    List<IFixedDepositPlansModel> fixedDepositPlansList = new ArrayList<>();
    IFixedDepositPlansModel fixedDepositPlansModel = new FixedDepositPlansModel();
    fixedDepositPlansModel.setId(10);
    fixedDepositPlansModel.setMinDuration(7);
    fixedDepositPlansModel.setMaxDuration(60);
    fixedDepositPlansModel.setInterest(2.9);
    fixedDepositPlansModel.setInterestSeniors(3.4);
    fixedDepositPlansModel.setType("short");
    fixedDepositPlansList.add(fixedDepositPlansModel);
    IFixedDepositPlansModel fixedDepositPlansModel2 =
            new FixedDepositPlansModel();
    fixedDepositPlansModel2.setId(11);
    fixedDepositPlansModel2.setMinDuration(60);
    fixedDepositPlansModel2.setMaxDuration(180);
    fixedDepositPlansModel2.setInterest(3.9);
    fixedDepositPlansModel2.setInterestSeniors(4.4);
    fixedDepositPlansModel2.setType("short");
    fixedDepositPlansList.add(fixedDepositPlansModel2);
    return fixedDepositPlansList;
  }

  @Override
  public boolean makeNewTransaction(double transferAmount, String operator, String comment) {
    return true;
  }

  @Override
  public double getCustomerBalance() {
    return MOCK_BALANCE;
  }
}
