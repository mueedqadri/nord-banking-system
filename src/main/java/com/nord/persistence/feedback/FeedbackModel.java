package com.nord.persistence.feedback;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;
import com.nord.persistence.feedback.interfaces.IFeedbackModel;
import com.nord.service.Context;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles feedbacks of the customer, it has methods to get feedback from user store it to the database,
 * fetch it from database and setting the status of the feedback
 * @author Harit Patwa
 */

public class FeedbackModel implements IFeedbackModel {
  
  @Override
  public boolean setFeedback(String feedbackText) {
    String query = "INSERT INTO customer_feedback\n" +
            "VALUES (" + Context.getLoggedInUserId() + ",\"" + feedbackText + "\",false);";
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
  @Override
  public List<String> getFeedback() {

    List<String> feedbacks = new ArrayList<>();
    String query = "SELECT feedback_text from customer_feedback where isViewed= false;";
    Statement statement = null;
    try {
      Connection dbConnection = DbConnection.getConnection();
      statement = dbConnection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while (resultSet.next()) {
        String feedback = resultSet.getString(ColumnNames.FEEDBACK_TEXT);
        feedbacks.add(feedback);
      }
      resultSet.close();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    DbConnection.closeDbConnection();
    return feedbacks;
  }

  @Override
  public boolean setFeedbackStatus() {
    String query = "UPDATE customer_feedback\n" +
            "SET isViewed = true;\n";
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
