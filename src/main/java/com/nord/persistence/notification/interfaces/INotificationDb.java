package com.nord.persistence.notification.interfaces;

/**
 * Interface which is implemented by NotificationDb class
 * @author Rahul Reddy Puchakayala
 */
public interface INotificationDb {

  /**
   * Retrieves notification from persistence
   * @return notification string
   */
  String getNotification();
}
