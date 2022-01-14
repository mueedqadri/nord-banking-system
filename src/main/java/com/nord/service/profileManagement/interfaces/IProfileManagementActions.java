package com.nord.service.profileManagement.interfaces;

import com.nord.service.profileManagement.ProfileManagementActions;
import com.nord.view.profileManagement.interfaces.IProfileManagementView;

/**
 * This interface is being implemented by ProfileManagementActions service class
 * @author Jay Nimeshkumar Patel, Rahul Reddy Puchakayala
 */
public interface IProfileManagementActions {

  /**
   * Sets the factory object for current class
   * @param factory object factory class
   * @return object of current class
   */
  IProfileManagementActions setFactory(IProfileManagementFactory factory);

  /**
   * Sets the view object for current class
   * @param profileView object of ProfileManagementView class
   * @return object of current class
   */
  ProfileManagementActions setView(IProfileManagementView profileView);

  /**
   * deactivates the account of current logged in user
   * @return true if account gets deactivated, false otherwise
   */
  boolean deactivateAccount();

  /**
   * changes the password and updates with the new string provided by the user
   * @return true if password gets updated, false otherwise
   */
  boolean modifyUserPassword();
}
