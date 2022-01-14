package com.nord.service.userManagement;

import com.nord.persistence.fixedDeposit.FixedDepositPlanPersistence;
import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlanPersistence;
import com.nord.persistence.rewardPoints.RewardPointsDb;
import com.nord.persistence.rewardPoints.interfaces.IRewardPointsDb;
import com.nord.service.bankingTransactions.TransactionRoot;
import com.nord.service.bankingTransactions.TransactionsFactory;
import com.nord.service.bankingTransactions.interfaces.ITransactionRoot;
import com.nord.service.bankingTransactions.interfaces.ITransactionsFactory;
import com.nord.service.billing.BillingService;
import com.nord.service.billing.interfaces.IBillingService;
import com.nord.service.creditCardServicesAndRecommendation.CreditCardRecommendationFactory;
import com.nord.service.creditCardServicesAndRecommendation.CreditCardRecommendationService;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.ICreditCardRecommendationFactory;
import com.nord.service.creditCardServicesAndRecommendation.interfaces.ICreditCardRecommendationService;
import com.nord.service.creditScoreLoan.CreditScoreLoanFactory;
import com.nord.service.creditScoreLoan.CreditScoreLoanService;
import com.nord.service.creditScoreLoan.interfaces.ICreditScoreLoanFactory;
import com.nord.service.creditScoreLoan.interfaces.ICreditScoreLoanService;
import com.nord.service.feedback.FeedbackFactory;
import com.nord.service.feedback.FeedbackServiceCustomerSide;
import com.nord.service.feedback.interrfaces.IFeedbackFactory;
import com.nord.service.feedback.interrfaces.IFeedbackServiceCustomerSide;
import com.nord.service.fixedDeposit.FixedDeposit;
import com.nord.service.fixedDeposit.FixedDepositFactory;
import com.nord.service.fixedDeposit.interfaces.IFixedDeposit;
import com.nord.service.fixedDeposit.interfaces.IFixedDepositFactory;
import com.nord.service.userManagement.interfaces.IMainFactory;
import com.nord.service.loan.LoanService;
import com.nord.service.loan.interfaces.ILoanService;
import com.nord.service.profileManagement.ProfileManagementFactory;
import com.nord.service.profileManagement.ProfileManagementService;
import com.nord.service.profileManagement.interfaces.IProfileManagementFactory;
import com.nord.service.profileManagement.interfaces.IProfileManagementService;
import com.nord.service.rewardPoints.RewardPoints;
import com.nord.service.rewardPoints.interfaces.IRewardPoints;
import com.nord.service.transactionHistory.TransactionHistoryFactory;
import com.nord.service.transactionHistory.TransactionHistoryService;
import com.nord.service.transactionHistory.interfaces.ITransactionHistoryFactory;
import com.nord.service.transactionHistory.interfaces.ITransactionHistoryService;
import com.nord.view.bankingTransactions.PayeesView;
import com.nord.view.bankingTransactions.TransactionsView;
import com.nord.view.bankingTransactions.interfaces.IPayeesView;
import com.nord.view.bankingTransactions.interfaces.ITransactionsView;
import com.nord.view.bill.BillingView;
import com.nord.view.bill.interfaces.IBillingView;
import com.nord.view.creditCardServicesAndRecommendation.CreditCardServicesAndRecommendationView;
import com.nord.view.creditCardServicesAndRecommendation.CreditCardTypesView;
import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardServicesAndRecommendationView;
import com.nord.view.creditCardServicesAndRecommendation.interfaces.ICreditCardTypesView;
import com.nord.view.creditScoreLoan.CreditScoreLoanView;
import com.nord.view.creditScoreLoan.interfaces.ICreditScoreLoanView;
import com.nord.view.feedback.FeedbackCustomerSideView;
import com.nord.view.feedback.interfaces.IFeedbackCustomerSideView;
import com.nord.view.fixedDeposit.FixedDepositMainView;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositMainView;
import com.nord.view.loan.LoanView;
import com.nord.view.loan.interfaces.ILoanView;
import com.nord.view.profileManagement.ProfileManagementView;
import com.nord.view.profileManagement.interfaces.IProfileManagementView;
import com.nord.view.rewardPoints.RewardPointView;
import com.nord.view.rewardPoints.interfaces.IRewardPointView;
import com.nord.view.transactionHistory.TransactionHistoryView;
import com.nord.view.transactionHistory.interfaces.ITransactionHistoryView;

/**
 * Factory class that implemented the provides the features for the customer
 * service
 * @author Abdul Mueed Qadri
 */
public class MainFactory implements IMainFactory {

  @Override
  public IFixedDeposit createFixedDeposit() {
    return new FixedDeposit().setFactory(createFdFactory()).setView(createFdView()).setPersistence(createFdPlanPersistence());
  }

  @Override
  public ICreditScoreLoanService createCreditScoreLoanService() {
    return new CreditScoreLoanService().setFactory(createCrsFactory()).setView(createCrsView());
  }

  @Override
  public IRewardPoints createRewardPoints() {
    return new RewardPoints().setRewardPointsDb(createRewardPointDb()).setRewardPointsView(createRewardPointView());
  }

  @Override
  public IFeedbackServiceCustomerSide createCustomerSideFeedback() {
    return new FeedbackServiceCustomerSide().setFeedbackFactory(createFeedbackFactory()).setView(createFeedbackCustomerSideView());
  }

  @Override
  public ITransactionRoot createTransactionRoot() {
    return new TransactionRoot().setFactory(createTransactionsFactory()).setPayeesView(createPayeesView()).setTransactionsView(createTransactionsView());
  }

  @Override
  public ICreditCardRecommendationService createCreditCardRecommendationService() {
    return new CreditCardRecommendationService().setFactory(createCreditCardRecommendationFactory()).setView(createCreditCardServicesAndRecommendationView()).setTypeView(createCreditCardTypesView());
  }

  @Override
  public IProfileManagementService createProfileManagementService() {
    return new ProfileManagementService().setFactory(createProfileManagementFactory()).setView(createProfileManagementView());
  }

  private IProfileManagementView createProfileManagementView() {
    return new ProfileManagementView();
  }

  private IProfileManagementFactory createProfileManagementFactory() {
    return new ProfileManagementFactory();
  }

  @Override
  public ITransactionHistoryService createTransactionHistoryService() {
    return new TransactionHistoryService().setFactory(createTransactionHistoryFactory()).setView(createTransactionHistoryView());
  }

  @Override
  public ILoanService createLoanService() {
    return new LoanService().setFactory(createLoanServiceFactory()).setView(createLoanView());
  }

  @Override
  public IBillingService createBillService() {
    return new BillingService().setFactory(createBillServiceFactory()).setView(createBillView());
  }

  private ITransactionHistoryFactory createTransactionHistoryFactory() {
    return new TransactionHistoryFactory();
  }

  private ITransactionHistoryView createTransactionHistoryView() {
    return new TransactionHistoryView();
  }

  private ICreditCardRecommendationFactory createCreditCardRecommendationFactory() {
    return new CreditCardRecommendationFactory();
  }

  private ICreditCardServicesAndRecommendationView createCreditCardServicesAndRecommendationView() {
    return new CreditCardServicesAndRecommendationView();
  }

  private ICreditCardTypesView createCreditCardTypesView() {
    return new CreditCardTypesView();
  }

  private ITransactionsFactory createTransactionsFactory() {
    return new TransactionsFactory();
  }

  private IPayeesView createPayeesView() {
    return new PayeesView();
  }

  private ITransactionsView createTransactionsView() {
    return new TransactionsView();
  }

  private IFeedbackFactory createFeedbackFactory() {
    return new FeedbackFactory();
  }

  private IFeedbackCustomerSideView createFeedbackCustomerSideView() {
    return new FeedbackCustomerSideView();
  }

  public IRewardPointsDb createRewardPointDb() {
    return new RewardPointsDb();
  }

  private IRewardPointView createRewardPointView() {
    return new RewardPointView();
  }

  private ICreditScoreLoanFactory createCrsFactory() {
    return new CreditScoreLoanFactory();
  }

  private ICreditScoreLoanView createCrsView() {
    return new CreditScoreLoanView();
  }

  private IFixedDepositFactory createFdFactory() {
    return new FixedDepositFactory();
  }

  private IFixedDepositMainView createFdView() {
    return new FixedDepositMainView();
  }

  public IFixedDepositPlanPersistence createFdPlanPersistence() {
    return new FixedDepositPlanPersistence();
  }

  private ILoanService createLoanServiceFactory() {
    return new LoanService();
  }

  private ILoanView createLoanView() {
    return new LoanView();
  }

  private IBillingService createBillServiceFactory() {
    return new BillingService();
  }

  private IBillingView createBillView() {
    return new BillingView();
  }
}
