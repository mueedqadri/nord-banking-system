package com.nord.persistence.profileManagement;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;
import com.nord.persistence.profileManagement.interfaces.IProfileManagement;
import com.nord.service.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class provides the operations to deactivate and change password of the current logged in user
 * with in the database
 * @author Rahul Reddy Puchakayala, Jay Nimeshkumar Patel
 */
public class ProfileManagement implements IProfileManagement {

  private Connection dbConnection;
  private PreparedStatement preparedStatement;

  @Override
  public boolean deactivateAccount() {
    try {
      dbConnection = DbConnection.getConnection();
      preparedStatement = dbConnection.prepareStatement("UPDATE customer_details set is_active=0 WHERE user_id=?");
      preparedStatement.setInt(1, Context.getLoggedInUserId());
      int result = preparedStatement.executeUpdate();
      if (result == 1) {
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  @Override
  public String getUsernameById() {
    try {
      dbConnection = DbConnection.getConnection();

      preparedStatement = dbConnection.prepareStatement("SELECT email from customer_details WHERE user_id=?;");
      preparedStatement.setInt(1, Context.getLoggedInUserId());
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        return resultSet.getString(ColumnNames.EMAIL);
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
      return null;
    }
    return null;
  }

  @Override
  public boolean changePassword(String newHashedPassword) {
    try {
      dbConnection = DbConnection.getConnection();

      preparedStatement = dbConnection.prepareStatement("UPDATE customer_details set password=? WHERE user_id=?;");
      preparedStatement.setString(1, newHashedPassword);
      preparedStatement.setInt(2, Context.getLoggedInUserId());
      int result = preparedStatement.executeUpdate();

      if (result == 1) {
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  @Override
  public void closeDbConnection() {
    DbConnection.closeDbConnection();
  }
}
