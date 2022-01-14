package com.nord.view;

import com.nord.view.interfaces.IMenuInterface;

public class NordBankApplicationView extends AbstractUserInterface implements IMenuInterface {

  @Override
  public void showMenu() {
    showHeader();
    System.out.println("1. Customer Login");
    System.out.println("2. Admin Login");
    System.out.println("3. Find a branch near you");
    System.out.println("4. Exit");
  }

  @Override
  public void showHeader() {
    System.out.println("=============================================================");
    System.out.println("           Welcome to Nord Bank Management System             ");
    System.out.println("=============================================================");
  }

  @Override
  public void showInvalidInput() {
    System.out.println("Something went wrong. Please try again!");
  }
}
