package com.nord.view.userManagement.interfaces;

import com.nord.view.interfaces.IUserInterface;

/**
 * Interface for Authentication View
 * @author mueed qdri
 */
public interface IUserAuthView extends IUserInterface {

    void showEnterUserName();

    void showEnterPassword();

    void checkCredentials();

    void newUser();

    void showNotification(String notification);
}
