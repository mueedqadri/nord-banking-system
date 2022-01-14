package com.nord.persistence.notification;

import com.nord.persistence.DbConnection;
import com.nord.service.Context;

import java.sql.*;
import java.time.LocalDate;

/**
 * Abstract class which insert a notification into database table
 * @author Rahul Reddy Puchakayala
 */
public abstract class AbstractNotificationDb {

  public void addNotification(String content, LocalDate triggerDate) {
    try {
      Date notificationDate = Date.valueOf(triggerDate);
      String query = "INSERT INTO customer_notifications (notification_date ,user_id, content)" + " VALUES (?, ?, ?)";
      Connection dbConnection = DbConnection.getConnection();
      PreparedStatement pstmt = dbConnection.prepareStatement(query);
      pstmt.setDate(1, notificationDate);
      pstmt.setInt(2, Context.getLoggedInUserId());
      pstmt.setString(3, content);
      pstmt.execute();
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    } catch (Exception e) {
      throw e;
    }
  }
}
