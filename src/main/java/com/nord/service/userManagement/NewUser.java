package com.nord.service.userManagement;

import com.nord.common.Constants;
import com.nord.persistence.userManagement.interfaces.ICustomerDetailsPersistence;
import com.nord.service.userManagement.interfaces.INewUser;
import com.nord.view.userManagement.interfaces.INewUserView;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class used to create a new User
 * @author Abdul Mueed Qadri
 */
public class NewUser implements INewUser {

  private ICustomerDetailsPersistence persistence;

  private boolean isAdmin;

  private INewUserView view;

  public NewUser setAdmin(boolean admin) {
    isAdmin = admin;
    return this;
  }

  public NewUser setPersistence(ICustomerDetailsPersistence persistence) {
    this.persistence = persistence;
    return this;
  }

  public NewUser setView(INewUserView view) {
    this.view = view;
    return this;
  }

  private Map<String, Object> prepareDetails(){
    return new LinkedHashMap<String, Object>() {{
      put(Constants.FIRST_NAME, null);
      put(Constants.MIDDLE_NAME, null);
      put(Constants.LAST_NAME, null);
      put(Constants.DOB, null);
      put(Constants.EMAIL, null);
      put(Constants.MOBILE_NO, null);
      put(Constants.STREET_NO, null);
      put(Constants.CITY, null);
      put(Constants.STATE, null);
      put(Constants.PIN_CODE, null);
      put(Constants.PASSPORT_NO, null);
      put(Constants.IS_STUDENT, null);
    }};
  }

  @Override
  public boolean createNewUserAccount(){
    int input;
    Map<String, Object> userDetails = prepareDetails();
    if(isAdmin){
      userDetails.remove(Constants.IS_STUDENT);
    }
    userDetails = view.addNewCustomer(userDetails);
    String userId = userDetails.get(Constants.EMAIL).toString();
    if(persistence.getUserId(userId) < 0){
      view.confirmDetails(userDetails);
      while(true) {
        input = view.getIntegerInput();
        if(input == 1){
          persistence.addNewCustomer(userDetails, isAdmin);
          view.accountAddedSuccessfully();
          return true;
        }else if(input == 2){
          return false;
        }
        view.showInvalidInputNumber();
      }
    } else {
      view.showUserExist(userId);
    }
    return false;
  }
}
