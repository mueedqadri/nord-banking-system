package com.nord.persistence.billing;

import com.nord.common.ColumnNames;
import com.nord.common.Constants;
import com.nord.persistence.DbConnection;
import com.nord.persistence.billing.interfaces.IBilling;
import com.nord.persistence.billing.interfaces.IBillingModel;
import com.nord.service.Context;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class performs CRUD functions that are related to the billing feature such as addBill, retrieveBill, deleteBill
 * @author Samir Anwar Rana
 */

public class Billing implements IBilling {

    private final int userId;

    public Billing() {
        this.userId = Context.getLoggedInUserId();
    }

    @Override
    public boolean addBillPayment(Map<String, Object> billDetails){
        boolean result = false;
        Connection connection = DbConnection.getConnection();
        try {
            Date dateOfPayment = Date.valueOf((LocalDate) billDetails.get(Constants.PAYMENT_DATE));
            String paymentCycleName = "";
            int paymentCycle = (int) billDetails.get(Constants.PAYMENT_CYCLE);
            if(paymentCycle == 1){
                paymentCycleName = Constants.DAILY;
            } else if (paymentCycle == 2){
                paymentCycleName = Constants.WEEKLY;
            } else if (paymentCycle == 3){
                paymentCycleName = Constants.MONTHLY;
            }  else if (paymentCycle == 4){
                paymentCycleName = Constants.YEARLY;
            }  else if (paymentCycle == 5){
                paymentCycleName = Constants.NONE;
            }
            String query = "INSERT INTO billing_payment (user_id, payee_name ,account_number, payment_info ,amount, payment_date, " +
                    "payment_cycle)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, userId);
            pstmt.setString(2, (String) billDetails.get(Constants.PAYEE_NAME));
            pstmt.setString(3, (String) billDetails.get(Constants.ACCOUNT_NO));
            pstmt.setString(4, (String) billDetails.get(Constants.PAYMENT_INFO));
            pstmt.setDouble(5, (Double) billDetails.get(Constants.AMOUNT));
            pstmt.setDate(6, dateOfPayment );
            pstmt.setString(7, paymentCycleName);
            pstmt.execute();
            result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<IBillingModel> retrieveBillsForUser() {
        List<IBillingModel> billingModelArrayList = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from billing_payment where user_id = "+userId+";");
            billingModelArrayList.add(getDetails(resultSet));
        } catch (SQLException throwable) {
            throwable.printStackTrace();

        }
        return billingModelArrayList;
    }

    private IBillingModel getDetails( ResultSet resultSet) throws SQLException {
        IBillingModel billingModel = new BillingModel();
        while (resultSet.next()){
            billingModel.setUserId(resultSet.getInt(ColumnNames.USER_ID));
            billingModel.setPayeeName(resultSet.getString(ColumnNames.PAYEE_NAME));
            billingModel.setAcctNumber(resultSet.getString(ColumnNames.ACCOUNT_NUMBER));
            billingModel.setPaymentInfo(resultSet.getString(ColumnNames.PAYMENT_INFO));
            billingModel.setAmount(resultSet.getInt(ColumnNames.AMOUNT));
            billingModel.setPaymentDate(resultSet.getDate(ColumnNames.PAYMENT_DATE));
            billingModel.setPaymentCycle(resultSet.getString(ColumnNames.PAYMENT_CYCLE));
        }
        return billingModel;
    }

    @Override
    public boolean deleteBill(String payeeName){
        Connection connection = DbConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE from billing_payment where payee_name = \""+payeeName+"\";");
            return true;

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<IBillingModel> retrieveAllBills() {
        List<IBillingModel> billingModelArrayList = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from billing_payment");
            billingModelArrayList.add(getDetails(resultSet));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return billingModelArrayList;
    }
}
