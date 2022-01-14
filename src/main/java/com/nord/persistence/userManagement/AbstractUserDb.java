package com.nord.persistence.userManagement;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class to implement methods that will be used across other classes
 * @author Abdul Mueed Qadri
 */
public abstract class AbstractUserDb {

  public int getUserId(String username){
    int id = -1;
    String query = "SELECT user_id FROM customer_details WHERE email='"+username+"';";
    Statement statement = null;
    try {
      Connection dbConnection = DbConnection.getConnection();
      statement = dbConnection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while(resultSet.next()){
        id = resultSet.getInt(ColumnNames.USER_ID);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return id;
  }
}
