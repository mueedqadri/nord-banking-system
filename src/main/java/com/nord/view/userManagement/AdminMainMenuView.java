package com.nord.view.userManagement;

import com.nord.view.userManagement.interfaces.IMainUserView;

/**
 * Display the menu for Admin
 * @author Abdul Mueed Qadri
 */
public class AdminMainMenuView extends AbstractUserAuthView  implements IMainUserView {

    @Override
    public void showMenu() {
        showHeader();
        System.out.println("1. Open New Bank Account");
        System.out.println("2. Work List");
        System.out.println("3. Enroll New Employee");
        System.out.println("4. View Feedbacks");
        System.out.println("5. Sign out");
    }

    @Override
    public void showHeader() {
        System.out.println("=============================================================");
        System.out.println("                          Admin Menu           ");
        System.out.println("=============================================================");
    }

    @Override
    public void showInvalidInput() {
        System.out.println("Please enter a valid input!");
    }
}
