package com.nord.view.userManagement;

import com.nord.view.userManagement.interfaces.IMainUserView;

/**
 * Shows menu out put to the customer
 * @author Abdul Mueed Qadri
 */
public class CustomerMainView extends AbstractUserAuthView implements IMainUserView {
    @Override
    public void showMenu() {
        System.out.println("1. Banking Transaction");
        System.out.println("2. Fixed Deposit");
        System.out.println("3. Loan");
        System.out.println("4. Credit Score Loan");
        System.out.println("5. User Profile Management");
        System.out.println("6. Transaction History/E-statement");
        System.out.println("7. Bills to pay");
        System.out.println("8. Credit Cards & Recommendation");
        System.out.println("9. Reward points");
        System.out.println("10. Write to us - Feedbacks");
        System.out.println("11. Log out");
        System.out.println("Enter your selection:");
    }

    @Override
    public void showHeader() {
        System.out.println("=============================================================");
        System.out.println("                      Customer Main Menu           ");
        System.out.println("=============================================================\n");
    }

    @Override
    public void showInvalidInput() {
        System.out.println("Please enter a valid input!");
    }
}
