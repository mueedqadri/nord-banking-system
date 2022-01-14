package com.nord.persistence.rewardPoints;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;
import com.nord.persistence.rewardPoints.interfaces.IRewardPointModel;
import com.nord.persistence.rewardPoints.interfaces.IRewardPointsDb;
import com.nord.service.Context;
import com.nord.common.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Persistence for handling reward points
 * @author Abdul Mueed Qadri
 */
public class RewardPointsDb extends AbstractRewardPoints implements IRewardPointsDb {

  @Override
  public List<IRewardPointModel> getRewardPoints(){
    List<IRewardPointModel> list = new ArrayList<>();
    removeExpiredPoints();
    String query = "SELECT * FROM reward_points " +
            "WHERE user_id="+ Context.getLoggedInUserId() +";";
    try {
      Connection dbConnection = DbConnection.getConnection();
      Statement statement = dbConnection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while(resultSet.next()){
        IRewardPointModel model = new RewardPointModel();
        model.setRewardPointBalance(resultSet.getInt(ColumnNames.REWARD_POINT_BALANCE));
        model.setRewardPointAddDate(resultSet.getDate(ColumnNames.REWARD_POINT_ADD_DATE).toLocalDate());
        model.setRewardPointExpireDate(resultSet.getDate(
                ColumnNames.REWARD_POINT_EXPIRE_DATE).toLocalDate());
        double dollarAmount =
                Utils.rewardToDollar(model.getRewardPointBalance());
        model.setDollarAmount(dollarAmount);
        list.add(model);
      }
      resultSet.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return list;
  }

  @Override
  public void removeAllPoints(){
    String query = "DELETE FROM reward_points  WHERE user_id="+ Context.getLoggedInUserId() +";";
    try {
      Connection dbConnection = DbConnection.getConnection();
      Statement statement = dbConnection.createStatement();
      statement.executeUpdate(query);
      statement.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
