package com.nord.persistence.userManagement;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;
import com.nord.persistence.userManagement.interfaces.IUserAuthDb;
import com.nord.common.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is used to handle the persistence part of the user authentication
 * @author Abdul Mueed Qadri
 */
public class UserAuthDb extends AbstractUserDb implements IUserAuthDb {

  @Override
  public String getHashPassword(String userName, boolean isAdmin){
    String result = "";
    String query =
            "SELECT password FROM customer_details WHERE email='"+userName+"'" +
                    " AND is_admin = "+isAdmin+" AND is_active = 1;";
    Statement statement = null;
    try {
      Connection dbConnection = DbConnection.getConnection();
      statement = dbConnection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while(resultSet.next()){
        result = resultSet.getString(ColumnNames.PASSWORD);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return result;
  }

  @Override
  public boolean isNewUser(String username){
    boolean result = false;
    String query = "SELECT is_new_customer FROM customer_details WHERE email='"+username+"';";
    Statement statement = null;
    try {
      Connection dbConnection = DbConnection.getConnection();
      statement = dbConnection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while(resultSet.next()){
        result = resultSet.getBoolean(ColumnNames.IS_NEW_CUSTOMER);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return result;
  }


  @Override
  public boolean setPassword(String password, String username){
    String query =
            "UPDATE customer_details SET password ='"+ Utils.encryptToSha256(password)+"', is_new_customer = 0" +
                    " WHERE email='"+username+"';";
    Statement statement = null;
    try {
      Connection dbConnection = DbConnection.getConnection();
      statement = dbConnection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }
}
