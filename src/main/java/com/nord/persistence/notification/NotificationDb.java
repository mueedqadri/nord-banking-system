package com.nord.persistence.notification;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;
import com.nord.persistence.notification.interfaces.INotificationDb;
import com.nord.service.Context;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class retrieves the notification to be triggered and sets the notification to seen if a user views it
 * @author Rahul Reddy Puchakayala
 */
public class NotificationDb implements INotificationDb {

  @Override
  public String getNotification() {
    int id = -1;
    String notification = "";
    try {
      Connection dbConnection = DbConnection.getConnection();
      Statement statement = dbConnection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM " + "customer_notifications  WHERE user_id = " + Context.getLoggedInUserId() + "" + " AND seen = 0 AND notification_date <= CURDATE();");
      if (resultSet.next()) {
        id = resultSet.getInt(ColumnNames.NOTIFICATION_ID);
        notification = resultSet.getString(ColumnNames.CONTENT);
      }
      resultSet.close();
      setNotificationSeen(id);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    return notification;
  }

  private void setNotificationSeen(int notificationId) {
    try {
      Connection dbConnection = DbConnection.getConnection();
      Statement statement = dbConnection.createStatement();
      statement.executeUpdate("UPDATE " + "customer_notifications SET seen = 1 WHERE  notification_id = " + notificationId + ";");
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
  }
}
