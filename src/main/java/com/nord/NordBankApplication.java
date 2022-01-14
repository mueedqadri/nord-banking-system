package com.nord;

import com.nord.service.Application;
import com.nord.service.interfaces.IApplication;
import com.nord.service.userManagement.UserFactory;
import com.nord.service.userManagement.interfaces.IUserFactory;
import com.nord.view.NordBankApplicationView;
import com.nord.view.interfaces.IMenuInterface;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class NordBankApplication {

  public static void main(String[] args) {

    IUserFactory factory = new UserFactory();
    IMenuInterface view = new NordBankApplicationView();
    IApplication app = new Application().setFactory(factory).setView(view);
    app.mainMenu();
  }
}
