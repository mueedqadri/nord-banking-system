package com.nord.service.userManagement;

import com.nord.persistence.branchLocator.BranchLocatorDb;
import com.nord.persistence.branchLocator.interfaces.IBranchLocatorDb;
import com.nord.persistence.notification.interfaces.INotificationDb;
import com.nord.persistence.notification.NotificationDb;
import com.nord.persistence.userManagement.CustomerDetailsPersistence;
import com.nord.persistence.userManagement.interfaces.ICustomerDetailsPersistence;
import com.nord.persistence.userManagement.interfaces.IUserAuthDb;
import com.nord.persistence.userManagement.UserAuthDb;
import com.nord.service.userManagement.interfaces.IMainFactory;
import com.nord.service.branchLocator.BranchLocatorService;
import com.nord.service.branchLocator.interfaces.IBranchLocator;
import com.nord.service.feedback.FeedbackFactory;
import com.nord.service.feedback.FeedbackServiceAdminSide;
import com.nord.service.feedback.interrfaces.IFeedbackFactory;
import com.nord.service.feedback.interrfaces.IFeedbackServiceAdminSide;
import com.nord.service.loan.LoanApproval;
import com.nord.service.loan.interfaces.ILoanApproval;
import com.nord.service.userManagement.interfaces.INewUser;
import com.nord.service.userManagement.interfaces.IUserFactory;
import com.nord.service.userManagement.interfaces.IUserService;
import com.nord.view.branchLocator.BranchLocatorView;
import com.nord.view.branchLocator.interfaces.IBranchLocatorView;
import com.nord.view.feedback.FeedbackAdminSideView;
import com.nord.view.feedback.interfaces.IFeedbackAdminSideView;
import com.nord.view.userManagement.AdminMainMenuView;
import com.nord.view.userManagement.CustomerMainView;
import com.nord.view.userManagement.interfaces.INewUserView;
import com.nord.view.userManagement.NewUserView;
import com.nord.view.userManagement.interfaces.IMainUserView;

/**
 * Factory to create objects needed form User
 * @author Abdul Mueed Qadri
 */
public class UserFactory implements IUserFactory {

  public IUserAuthDb createUserAuthDb(){
    return new UserAuthDb();
  }

  public IMainFactory createMainFactory(){
    return new MainFactory();
  }

  @Override
  public IUserService createCustomer(){
    return new CustomerService()
            .setFactory(createMainFactory())
            .setNotification(createNotificationDb())
            .setView(createCustomerMenuView())
            .setDbObj(createUserAuthDb());
  }

  @Override
  public IUserService createAdmin(){
    return new AdminService()
        .setFactory(this)
        .setView(createAdminMenuView())
        .setDbObj(createUserAuthDb());
  }

  public INotificationDb createNotificationDb(){
    return new NotificationDb();
  }

  public IMainUserView createAdminMenuView(){
    return new AdminMainMenuView();
  }

  public IMainUserView createCustomerMenuView(){
    return new CustomerMainView();
  }

  public INewUserView createNewUserView(){
    return new NewUserView();
  }

  public ICustomerDetailsPersistence createDetailsModel(){
    return new CustomerDetailsPersistence();
  }

  @Override
  public INewUser createNewCustomer(){
    return new NewUser()
            .setAdmin(false)
            .setPersistence(createDetailsModel())
            .setView(createNewUserView());
  }

  @Override
  public INewUser createNewEmployee(){
    return new NewUser()
            .setAdmin(true)
            .setPersistence(createDetailsModel())
            .setView(createNewUserView());
  }

  public IFeedbackFactory createFeedbackFactory(){
    return new FeedbackFactory();
  }

  public IFeedbackAdminSideView createFeedbackAdminSideView(){
    return new FeedbackAdminSideView();
  }

  @Override
  public IFeedbackServiceAdminSide createFeedbackAdminService(){
    return new FeedbackServiceAdminSide()
            .setFactory(createFeedbackFactory())
            .setView(createFeedbackAdminSideView());
  }

  public IBranchLocatorDb createBranchLocatorModel() {
    return new BranchLocatorDb();
  }

  public IBranchLocatorView createBranchLocatorView(){
    return new BranchLocatorView();
  }

  @Override
  public IBranchLocator createBranchLocator(){
    return new BranchLocatorService()
        .setFactory(this)
        .setDb(createBranchLocatorModel())
        .setView(createBranchLocatorView());
  }

  @Override
  public ILoanApproval createLoanApproval(){ return new LoanApproval();}
}
