package com.nord.view.userManagement;

import com.nord.view.AbstractUserInterface;

/**
 * Abstract class for handling Authentication IO
 * @author Abdul Mueed Qadri
 */
public abstract class AbstractUserAuthView extends AbstractUserInterface {

    public void showEnterUserName(){
        System.out.println("Enter your email:");
    }

    public void showEnterPassword(){
        System.out.println("Enter your password:");
    }

    public void checkCredentials(){
        System.out.println("Please check your credentials!");
        System.out.println("0. Go to previous menu");
        System.out.println("1. Try again");
    }

    public void newUser(){
        System.out.println("Please set your new password");
    }

    public void showNotification(String notification){
        if(!notification.isEmpty()){
            System.out.println("****** "+notification+" ******");
        }
    }
}
