package com.nord.service.userManagement;

import com.nord.persistence.userManagement.interfaces.IUserAuthDb;
import com.nord.service.Context;
import com.nord.common.Utils;
import com.nord.view.userManagement.interfaces.IMainUserView;
import com.nord.view.userManagement.interfaces.IUserAuthView;

/**
 * Service used by both Customer Service as well by Admin Service to do
 * authentication of the user
 * @author Abdul Mueed Qadri
 */
public abstract class UserAuthService {

    private IUserAuthDb db;

    private IUserAuthView view;

    private boolean isAdmin;

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setAuthView(IMainUserView view) {
        this.view = view;
    }

    public void setDb(IUserAuthDb db) {
        this.db = db;
    }

    public boolean login() {
        view.showEnterUserName();
        String userName = view.getValidEmailInput();
        view.showEnterPassword();
        String enteredPassword = view.getStringInputNoValidation();
        String password = db.getHashPassword(userName, isAdmin);
        if (password.equals(Utils.encryptToSha256(enteredPassword))) {
            isNewUser(userName);
            Context.setLoggedInUserId(db.getUserId(userName));
            return true;
        }
        view.checkCredentials();
        return false;
    }

    private void isNewUser(String userName){
        if (db.isNewUser(userName)){
            view.newUser();
            db.setPassword(view.getStringInputNoValidation() , userName);
        }
    }
}
