package com.nord.service.userManagement;

import com.nord.persistence.userManagement.interfaces.IUserAuthDb;
import com.nord.service.Context;
import com.nord.service.feedback.FeedbackFactory;
import com.nord.service.feedback.FeedbackServiceAdminSide;
import com.nord.service.feedback.interrfaces.IFeedbackFactory;
import com.nord.service.feedback.interrfaces.IFeedbackServiceAdminSide;
import com.nord.service.userManagement.interfaces.IUserFactory;
import com.nord.service.userManagement.interfaces.IUserService;
import com.nord.view.feedback.FeedbackAdminSideView;
import com.nord.view.feedback.interfaces.IFeedbackAdminSideView;
import com.nord.view.userManagement.interfaces.IMainUserView;

/**
 * Handles the Admin Side. After an admin is successfully logged in admin
 * home page is displayed
 * @author Abdul Mueed Qadri
 */
public class AdminService extends UserAuthService implements IUserService {

    private IMainUserView view;

    private IUserFactory factory;

    public AdminService(){
        super.setAdmin(true);
    }

    public AdminService setFactory(IUserFactory factory) {
        this.factory = factory;
        return this;
    }

    public AdminService setDbObj(IUserAuthDb db){
        super.setDb(db);
        return this;
    }

    public AdminService setView(IMainUserView view) {
        super.setAuthView(view);
        this.view = view;
        return this;
    }

    @Override
    public void homePage(){
        while (true){
            view.showMenu();
            int input = view.getIntegerInput();
            switch (input){
                case 1:
                    factory.createNewCustomer().createNewUserAccount();
                    break;
                case 2:
                    factory.createLoanApproval();
                    break;
                case 3:
                    factory.createNewEmployee().createNewUserAccount();
                    break;
                case 4:
                    IFeedbackFactory feedbackFactory = new FeedbackFactory();
                    IFeedbackAdminSideView feedbackAdminSideView = new FeedbackAdminSideView();
                    IFeedbackServiceAdminSide feedbackServiceAdminSide = new FeedbackServiceAdminSide()
                            .setView(feedbackAdminSideView)
                            .setFactory(feedbackFactory);
                    feedbackServiceAdminSide.showFeedbacks();
                    break;
                case 5:
                    Context.setLoggedInUserId(0);
                    return;
            }
        }
    }
}
