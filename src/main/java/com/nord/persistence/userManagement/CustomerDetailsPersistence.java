package com.nord.persistence.userManagement;

import com.nord.common.Constants;
import com.nord.persistence.DbConnection;
import com.nord.persistence.userManagement.interfaces.ICustomerDetailsPersistence;
import com.nord.common.Utils;

import java.sql.*;
import java.time.LocalDate;
import java.util.Map;

/**
 * Persistence to handle a new user creation to the database
 * @author Abdul Mueed Qadri
 */
public class CustomerDetailsPersistence extends AbstractUserDb implements ICustomerDetailsPersistence {
    @Override
    public boolean addNewCustomer(Map<String, Object> customerDetails,
                                  boolean isAdmin){
        boolean result = false;
        Boolean isStudent = false;
        Connection connection = DbConnection.getConnection();
        try {
            Date dob = Date.valueOf((LocalDate) customerDetails.get(Constants.DOB));
            if (!isAdmin){
                isStudent =  customerDetails.get(Constants.IS_STUDENT).equals(Constants.YES_Y);
            }
            String query = "INSERT INTO customer_details (firstname ,middlename, lastname, dateofbirth, email,mobile," +
                    " streetno , city, state, pin_code, passportno, " +
                    "isStudent, password, withdraw_limit, spending_limit,is_admin)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, (String) customerDetails.get(Constants.FIRST_NAME));
            pstmt.setString(2, (String) customerDetails.get(Constants.MIDDLE_NAME));
            pstmt.setString(3, (String) customerDetails.get(Constants.LAST_NAME));
            pstmt.setDate(4, dob );
            pstmt.setString(5, (String) customerDetails.get(Constants.EMAIL));
            pstmt.setInt(6, (Integer) customerDetails.get(Constants.MOBILE_NO));
            pstmt.setString(7, (String) customerDetails.get(Constants.STREET_NO));
            pstmt.setString(8, (String) customerDetails.get(Constants.CITY));
            pstmt.setString(9, (String) customerDetails.get(Constants.STATE));
            pstmt.setString(10, (String) customerDetails.get(Constants.PIN_CODE));
            pstmt.setString(11, (String) customerDetails.get(Constants.PASSPORT_NO));
            pstmt.setBoolean(12, isStudent);
            pstmt.setString(13, Utils.encryptToSha256((String) customerDetails.get(Constants.EMAIL)));
            pstmt.setDouble(14, isStudent? 2000: 3000);
            pstmt.setDouble(15, isStudent? 2000: 3000);
            pstmt.setBoolean(16, isAdmin);
            pstmt.execute();
            result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
