package com.nord.view.userManagement;

import com.nord.view.userManagement.interfaces.INewUserView;

import java.util.Locale;
import java.util.Map;

/**
 * Handles IO when creating a new user
 * @author meed qadri
 */
public class NewUserView extends AdminMainMenuView implements INewUserView {

  @Override
  public Map<String, Object> addNewCustomer(Map<String, Object> customerDetails){
    showHeader();
    System.out.println("Please Enter Details: ");
    for(Map.Entry<String, Object> item : customerDetails.entrySet()){
      System.out.println("Enter "+item.getKey()+" *");
      if(item.getKey().toLowerCase(Locale.ROOT).contains("email")){
        item.setValue(getValidEmailInput());
      } else if(item.getKey().toLowerCase(Locale.ROOT).contains("date of birth")){
        item.setValue(getDateInputLessThanToday());
      } else if(item.getKey().toLowerCase(Locale.ROOT).contains("mobile number")){
        item.setValue(getValidPhoneNo());
      }else {
        item.setValue(getStringInputNoValidation());
      }
    }
    return customerDetails;
  }

  @Override
  public void confirmDetails(Map<String, Object> customerDetails){
    showHeader();
    for(Map.Entry<String, Object> item : customerDetails.entrySet()){
      System.out.println(String.format("%-40s: %-20s", item.getKey(), item.getValue()));
    }
    System.out.println("\n1. Confirm details");
    System.out.println("2. Back to main menu");
  }

  @Override
  public void accountAddedSuccessfully(){
    System.out.println("Customer created successfully ");
    System.out.println("0. Back to main menu");
    while (true){
      int input = getIntegerInput();
      if (input == 0){
        return;
      }
      showInvalidInputNumber();
    }
  }

  @Override
  public void showUserExist(String email) {
    int input;
    do{
      System.out.println("User with email: "+email+" already exists");
      System.out.println("0. Back to main menu");
      input = getIntegerInput();
    }while (input !=0);
  }
}
