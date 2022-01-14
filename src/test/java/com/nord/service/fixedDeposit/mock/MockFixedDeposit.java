package com.nord.service.fixedDeposit.mock;

import com.nord.persistence.fixedDeposit.UserFixedDepositModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class was crete to generate mock data for testing Fixed Deposit Service
 * @author Abdul Mueed Qadri
 */
public class MockFixedDeposit {

    public IUserFixedDepositModel getMockFd(){
        IUserFixedDepositModel userFixedDepositModel = new UserFixedDepositModel();
        String withdrawalDate = "20/08/2021";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        userFixedDepositModel.setFdId(4);
        userFixedDepositModel.setPlanId(10);
        userFixedDepositModel.setWithdrawalDate(LocalDate.parse(withdrawalDate, formatter));
        userFixedDepositModel.setStartDate(LocalDate.now());
        userFixedDepositModel.setAmount(500);
        userFixedDepositModel.setProfit(14.5);
        userFixedDepositModel.setInterest(2.9);
        return userFixedDepositModel;
    }

    public IUserFixedDepositModel getMockFdWithSuggestion(){
        IUserFixedDepositModel userFixedDepositModel =
                new UserFixedDepositModel();
        LocalDate triggerDate = LocalDate.now().plusDays(57);
        userFixedDepositModel.setFdId(4);
        userFixedDepositModel.setPlanId(10);
        userFixedDepositModel.setWithdrawalDate(triggerDate);
        userFixedDepositModel.setStartDate(LocalDate.now());
        userFixedDepositModel.setAmount(1000);
        userFixedDepositModel.setProfit(29);
        userFixedDepositModel.setInterest(2.9);
        return userFixedDepositModel;
    }
}