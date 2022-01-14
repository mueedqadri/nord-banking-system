package com.nord.persistence.creditScoreLoan;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;
import com.nord.persistence.creditScoreLoan.interfaces.ICustomerBalanceManagement;
import com.nord.service.Context;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class handles the customer balance for the user, it performs operation such as get balance, set balance with the database
 * @author Harit Patwa
 */

public class CustomerBalanceManagement implements ICustomerBalanceManagement {

  private int customerBalance;


  @Override
  public int getCustomerBalance() {


    String query = "SELECT account_balance FROM customer_details where user_id ="+Context.getLoggedInUserId()+";";
    Statement statement = null;
    try {
      Connection dbConnection = DbConnection.getConnection();
      statement = dbConnection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while(resultSet.next()){
        this.customerBalance = resultSet.getInt(ColumnNames.ACCOUNT_BALANCE);
      }

      resultSet.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    DbConnection.closeDbConnection();
    return  this.customerBalance;

  }
  @Override
  public void setCustomerBalance(int balance) {
    String query = "UPDATE customer_details\n" +
            "SET account_balance = "+balance+"\n" +
            "WHERE user_id = "+ Context.getLoggedInUserId()+";" ;
    Statement statement = null;

    Connection dbConnection = DbConnection.getConnection();
    try {
      statement = dbConnection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    DbConnection.closeDbConnection();
  }
}
