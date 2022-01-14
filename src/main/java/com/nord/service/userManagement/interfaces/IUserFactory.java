package com.nord.service.userManagement.interfaces;

import com.nord.service.branchLocator.interfaces.IBranchLocator;
import com.nord.service.feedback.interrfaces.IFeedbackServiceAdminSide;
import com.nord.service.loan.interfaces.ILoanApproval;

/**
 * Interface used by User Factory
 * @author Abdul Mueed Qadri
 */
public interface IUserFactory {

  IUserService createCustomer();

  IUserService createAdmin();

  INewUser createNewCustomer();

  INewUser createNewEmployee();

  IFeedbackServiceAdminSide createFeedbackAdminService();

  IBranchLocator createBranchLocator();

  ILoanApproval createLoanApproval();
}
