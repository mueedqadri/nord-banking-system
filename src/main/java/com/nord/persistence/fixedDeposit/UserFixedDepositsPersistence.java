package com.nord.persistence.fixedDeposit;

import com.nord.common.ColumnNames;
import com.nord.common.Constants;
import com.nord.persistence.DbConnection;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositsPersistence;
import com.nord.persistence.rewardPoints.AbstractRewardPoints;
import com.nord.service.Context;
import com.nord.common.Utils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Persistence to handle the user FDs
 * @author Abdul Mueed Qadri
 */
public class UserFixedDepositsPersistence extends AbstractRewardPoints implements IUserFixedDepositsPersistence {

    @Override
    public boolean addFdToUser(IUserFixedDepositModel fd) {
        try {
            Date startDateDb = Date.valueOf(LocalDate.now());
            Date closedDateDb = Date.valueOf(fd.getWithdrawalDate());
            String query = "INSERT INTO customer_fixed_deposit (fd_id ,user_id, start_date,withdrawal_date," +
                    "initial_amount,withdrawal_amount, plan_id)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            Connection dbConnection = DbConnection.getConnection();
            PreparedStatement pstmt = dbConnection.prepareStatement(query);
            pstmt.setInt(1, 0);
            pstmt.setInt(2, Context.getLoggedInUserId());
            pstmt.setDate(3, startDateDb);
            pstmt.setDate(4, closedDateDb);
            pstmt.setDouble(5, fd.getAmount());
            pstmt.setDouble(6, fd.getAmount() + fd.getProfit());
            pstmt.setInt(7, fd.getPlanId());
            pstmt.execute();
            if(fd.getIsUpgraded()){
                addRewardPoints(Utils.calculateRewardPointFd(fd));
            }
            addNotification(Constants.FIXED_DEPOSIT_NOTIF
                , fd.getWithdrawalDate().minusDays(15));
            makeNewTransaction(fd.getAmount(), Constants.SUBTRACT, Constants.FIXED_DEPOSIT);
            return true;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    @Override
    public List<IUserFixedDepositModel> getAllFdsForUser() {
        List<IUserFixedDepositModel> userFixedDepositModels = new ArrayList<>();
        try {
            Connection dbConnection = DbConnection.getConnection();
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery("SELECT * FROM customer_fixed_deposit WHERE user_id='" + Context.getLoggedInUserId() +
                    "';");
            while (resultSet.next()) {
                IUserFixedDepositModel userFixedDepositModel = new UserFixedDepositModel();
                userFixedDepositModel.setPlanId(resultSet.getInt(ColumnNames.PLAN_ID));
                userFixedDepositModel.setFdId(resultSet.getInt(ColumnNames.FD_ID));
                userFixedDepositModel.setStartDate(resultSet.getDate(ColumnNames.START_DATE).toLocalDate());
                userFixedDepositModel.setWithdrawalDate(resultSet.getDate(ColumnNames.WITHDRAWAL_DATE).toLocalDate());
                userFixedDepositModel.setAmount(resultSet.getFloat(ColumnNames.INITIAL_AMOUNT));
                userFixedDepositModel.setProfit(resultSet.getFloat(ColumnNames.WITHDRAWAL_AMOUNT) - userFixedDepositModel.getAmount());
                userFixedDepositModels.add(userFixedDepositModel);
            }
            resultSet.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return userFixedDepositModels;
    }

    @Override
    public boolean withdrawFd(int fdId, double amount){
        boolean result = false;
        try {
            Connection dbConnection = DbConnection.getConnection();
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate("DELETE FROM customer_fixed_deposit " +
                    "WHERE fd_id='" +fdId +"';");
            makeNewTransaction(amount, Constants.ADDITION, Constants.FIXED_DEPOSIT);
            result = true;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
