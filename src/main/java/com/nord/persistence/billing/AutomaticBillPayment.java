package com.nord.persistence.billing;

import com.nord.persistence.DbConnection;
import com.nord.persistence.billing.interfaces.IAutomaticBillPayment;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class performs the automatic bill deduction where it deducts money of the user in a time period
 * @author Samir Anwar Rana
 */
public class AutomaticBillPayment implements IAutomaticBillPayment {

    @Override
    public boolean deductMoney(int userId, double amount){
        boolean result = false;
        Connection connection = DbConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE customer_details SET account_balance = account_balance - \""+amount+"\"  WHERE user_id = \""+userId+"\";");
            result = true;

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return result;
    }
}
