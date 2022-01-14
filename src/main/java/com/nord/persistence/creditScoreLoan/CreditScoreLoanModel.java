package com.nord.persistence.creditScoreLoan;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;
import com.nord.persistence.creditScoreLoan.interfaces.ICreditScoreLoanModel;
import com.nord.service.Context;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * This class handles operations of Credit score loan such as apply loan, set status and get count of credit loan with the database
 * @author Harit Patwa
 */

public class CreditScoreLoanModel implements ICreditScoreLoanModel {

  private int amount;
  private Date dueDate;
  private boolean isPaid;


  @Override
  public int getAmount() {
    return amount;
  }

  @Override
  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Override
  public Date getDueDate() {
    return dueDate;
  }

  @Override
  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  @Override
  public boolean getPaidStatus() {
    return isPaid;
  }

  @Override
  public void setPaid(boolean isPaid) {
    this.isPaid = isPaid;
  }

  @Override
  public boolean applyForCreditScoreLoan(int amount, String strDate) {
    String query = "INSERT INTO customer_crs(user_id,amount,isPaid,duedate)\n" +
            "VALUES (" + Context.getLoggedInUserId() + "," + amount + ",false,'" + strDate + "');";
    Statement statement = null;

    Connection dbConnection = DbConnection.getConnection();
    try {
      statement = dbConnection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    DbConnection.closeDbConnection();
    return true;
  }

  @Override
  public int getCreditScoreLoanCount() {
    int count = 0;
    String query = "SELECT * FROM customer_crs where user_id = " + Context.getLoggedInUserId() + ";";
    Statement statement = null;
    try {
      Connection dbConnection = DbConnection.getConnection();
      statement = dbConnection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while (resultSet.next()) {
        if(! resultSet.getBoolean(ColumnNames.IS_PAID)) {
          count++;
        }
      }

      resultSet.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    DbConnection.closeDbConnection();
    return count;
  }

  @Override
  public void setCreditScoreLoanPayStatus(int am1) {
    String query = "UPDATE customer_crs\n" +
            "SET isPaid =true\n" +
            "where user_id = " + Context.getLoggedInUserId() + " and amount = " + am1 + ";";
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
