package com.nord.service.userManagement;

import com.nord.persistence.notification.interfaces.INotificationDb;
import com.nord.persistence.userManagement.interfaces.IUserAuthDb;
import com.nord.service.Context;
import com.nord.service.userManagement.interfaces.IMainFactory;
import com.nord.service.userManagement.interfaces.IUserService;
import com.nord.view.userManagement.interfaces.IMainUserView;

/**
 * Handles the Customer Side. After an customer is successfully logged in
 * customer home page is displayed
 * @author Abdul Mueed Qadri
 */
public class CustomerService extends UserAuthService implements IUserService {

    private IMainUserView view;

    private INotificationDb notificationDb;

    private IMainFactory factory;

    public CustomerService(){
        super.setAdmin(false);
    }

    public CustomerService setDbObj(IUserAuthDb db){
        setDb(db);
        return this;
    }

    public CustomerService setFactory(IMainFactory factory) {
        this.factory = factory;
        return this;
    }

    public CustomerService setNotification(INotificationDb notification) {
        this.notificationDb = notification;
        return this;
    }

    public CustomerService setView(IMainUserView view){
        setAuthView(view);
        this.view = view;
        return this;
    }

    @Override
    public void homePage(){
        while (true){
            view.showHeader();
            view.showNotification(notificationDb.getNotification());
            view.showMenu();
            int input = view.getIntegerInput();
            switch (input){
                case 1:
                    factory.createTransactionRoot().menuOptions();
                    break;
                case 2:
                    factory.createFixedDeposit().fixedDepositHomePage();
                    break;
                case 3:
                    factory.createLoanService().LoanHomePage();
                    break;
                case 4:
                    factory.createCreditScoreLoanService().creditScoreLoanHomePage();
                    break;
                case 5:
                    factory.createProfileManagementService().menuOptions();
                    break;
                case 6:
                    factory.createTransactionHistoryService().transactionMenu();
                    break;
                case 7:
                   factory.createBillService().billingHomePage();
                    break;
                case 8:
                    factory.createCreditCardRecommendationService().creditCardRecommendationHomepage();
                    break;
                case 9:
                    factory.createRewardPoints().rewardMenu();
                    break;
                case 10:
                    factory.createCustomerSideFeedback().feedbackHomePage();
                    break;
                case 11:
                    Context.setLoggedInUserId(0);
                    return;
                default:
                    view.showInvalidInput();
            }
        }
    }
}
