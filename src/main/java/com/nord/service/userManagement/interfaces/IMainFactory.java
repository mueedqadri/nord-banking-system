package com.nord.service.userManagement.interfaces;

import com.nord.service.bankingTransactions.interfaces.ITransactionRoot;
import com.nord.service.billing.interfaces.IBillingService;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.ICreditCardRecommendationService;
import com.nord.service.creditScoreLoan.interfaces.ICreditScoreLoanService;
import com.nord.service.feedback.interrfaces.IFeedbackServiceCustomerSide;
import com.nord.service.fixedDeposit.interfaces.IFixedDeposit;
import com.nord.service.loan.interfaces.ILoanService;
import com.nord.service.profileManagement.interfaces.IProfileManagementService;
import com.nord.service.rewardPoints.interfaces.IRewardPoints;
import com.nord.service.transactionHistory.interfaces.ITransactionHistoryService;

/**
 * Interface Implemented by MainFactory
 * @author Abdul Mueed Qadri
 */
public interface IMainFactory {

  IFixedDeposit createFixedDeposit();

  ICreditScoreLoanService createCreditScoreLoanService();

  IProfileManagementService createProfileManagementService();

  IRewardPoints createRewardPoints();

  IFeedbackServiceCustomerSide createCustomerSideFeedback();

  ITransactionRoot createTransactionRoot();

  ICreditCardRecommendationService createCreditCardRecommendationService();

  ITransactionHistoryService createTransactionHistoryService();

  ILoanService createLoanService();

  IBillingService createBillService();

}
