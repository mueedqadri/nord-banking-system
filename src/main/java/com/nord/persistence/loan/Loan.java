package com.nord.persistence.loan;

import com.nord.common.ColumnNames;
import com.nord.common.Constants;
import com.nord.persistence.DbConnection;
import com.nord.persistence.loan.interfaces.ILoan;
import com.nord.persistence.loan.interfaces.ILoanModel;
import com.nord.service.Context;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * This class performs the operations of the Loan feature such as adding Loans and retrieving Loans
 * @author Samir Anwar Rana
 */

public class Loan implements ILoan {

    private final int userId;

    public Loan() {
        this.userId = Context.getLoggedInUserId();
    }

    @Override
    public boolean addLoan(Map<String, Object> loanDetails){
        boolean result = false;
        Connection connection = DbConnection.getConnection();
        try {
            Date dateOfPayment = Date.valueOf((LocalDate) LocalDate.now());
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateOfPayment);
            cal.add(Calendar.YEAR,1);
            java.util.Date repayment = cal.getTime();
            Date repaymentDate = new java.sql.Date(repayment.getTime());
            String loanName = "";
            int interestRate = 0;
            int loanType = (int) loanDetails.get(Constants.LOAN_TYPE);
            if(loanType == 1){
                loanName = Constants.STUDENT;
                interestRate = 5;
            } else if (loanType == 2){
                loanName = Constants.PERSONAL;
                interestRate = 7;
            } else if (loanType == 3){
                loanName = Constants.BUSINESS;
                interestRate = 9;
            }
            String query = "INSERT INTO customer_loan (user_id, loan_type , amount, loan_date, repayment_date, interest_rate, status)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, userId);
            pstmt.setString(2, loanName);
            pstmt.setDouble(3, (Double) loanDetails.get(Constants.AMOUNT));
            pstmt.setDate(4, dateOfPayment);
            pstmt.setDate(5, repaymentDate);
            pstmt.setInt(6, interestRate);
            pstmt.setBoolean(7, false);
            pstmt.execute();
            result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<ILoanModel> retrieveLoansForUser() {
        List<ILoanModel> loanModelArrayList = new ArrayList<>();
        Connection connection = DbConnection.getConnection();

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("Select * from customer_loan where user_id = "+userId+";");

            while (resultSet.next()){
                ILoanModel loanModel = new LoanModel();
                loanModel.setUserId(resultSet.getInt(ColumnNames.USER_ID));
                loanModel.setLoanType(resultSet.getString(ColumnNames.LOAN_TYPE));
                loanModel.setAmount(resultSet.getDouble(ColumnNames.AMOUNT));
                loanModel.setAppliedDate(resultSet.getDate(ColumnNames.LOAN_DATE));
                loanModel.setRepaymentDate(resultSet.getDate(ColumnNames.REPAYMENT_DATE));
                loanModel.setInterestRate(resultSet.getInt(ColumnNames.INTEREST_RATE));
                loanModel.setStatus(resultSet.getBoolean(ColumnNames.LOAN_ID));
                loanModelArrayList.add(loanModel);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return loanModelArrayList;
    }
}
