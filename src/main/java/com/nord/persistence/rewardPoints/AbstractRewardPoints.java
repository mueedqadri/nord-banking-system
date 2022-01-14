package com.nord.persistence.rewardPoints;

import com.nord.persistence.DbConnection;
import com.nord.persistence.bankingTransactions.AbstractBanking;
import com.nord.service.Context;

import java.sql.*;
import java.time.LocalDate;

/**
 * Abstract class implemented by FD to add the Reward to the user on
 * successful FD creation
 * @author Abdul Mueed Qadri
 */
public abstract class AbstractRewardPoints extends AbstractBanking {
  private final int EXPIRY_PERIOD = 3;

  public boolean addRewardPoints(int pointsToAdd){
    boolean result = false;
    removeExpiredPoints();
    Date addDate = Date.valueOf(LocalDate.now());
    Date expDate = Date.valueOf(LocalDate.now().plusMonths(EXPIRY_PERIOD));
    String query = "INSERT INTO reward_points(reward_point_add_date, " +
            "reward_point_expire_date, reward_point_balance, user_id) " +
            "VALUES (?, ?, ?, ?)";
    try {
      Connection dbConnection = DbConnection.getConnection();
      PreparedStatement statement = dbConnection.prepareStatement(query);
      statement.setDate(1, addDate);
      statement.setDate(2, expDate);
      statement.setInt(3, pointsToAdd);
      statement.setInt(4, Context.getLoggedInUserId());
      statement.execute();
      result = true;
    } catch (SQLException throwables){
      throwables.printStackTrace();
    }
    return result;
  }

  public void removeExpiredPoints(){
    String query = "DELETE FROM reward_points WHERE " +
            "reward_point_expire_date <= CURDATE() AND user_id = "+ Context.getLoggedInUserId() +";";
    try {
      Connection dbConnection = DbConnection.getConnection();
      Statement statement = dbConnection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
