package com.nord.persistence.creditCardServicesAndRecommendation;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;
import com.nord.persistence.creditCardServicesAndRecommendation.interfaces.ICreditCardDetailsModel;
import com.nord.service.Context;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * this class fetches,updates and sets credit card details of the perticular user
 * @author Harit Patwa
 */

public class CreditCardDetailsModel implements ICreditCardDetailsModel {


  @Override
  public String getCreditCardDetails() {
    String creditCardName =null;
    String query = "SELECT * FROM customer_credit_card_details where userId = "+Context.getLoggedInUserId()+";";
    Statement statement = null;
    try {
      Connection dbConnection = DbConnection.getConnection();
      statement = dbConnection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while (resultSet.next()) {
        creditCardName = resultSet.getString(ColumnNames.CREDIT_CARD_NAME);
      }
      resultSet.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    DbConnection.closeDbConnection();
    return creditCardName;
  }

  @Override
  public boolean setCreditCardDetails(String creditCardName) {

    String query = "UPDATE customer_credit_card_details\n" +
            "SET credit_card_name = \""+creditCardName+"\"\n" +
            "where userId="+Context.getLoggedInUserId()+";";
    return executeQuery(query);
  }

  @Override
  public boolean insertCreditCardDetails(String creditCardName) {

    String query = "INSERT INTO customer_credit_card_details(userId,credit_card_name)\n" +
            "VALUES ("+Context.getLoggedInUserId()+",\""+creditCardName+"\");";
    return executeQuery(query);
  }

  private boolean executeQuery(String query)
  {
    Statement statement = null;

    Connection dbConnection = DbConnection.getConnection();
    try {
      statement = dbConnection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException throwables) {
      return false;
    }
    DbConnection.closeDbConnection();
    return true;
  }
}

