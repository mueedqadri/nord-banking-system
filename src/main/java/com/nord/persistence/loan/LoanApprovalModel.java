package com.nord.persistence.loan;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;
import com.nord.persistence.loan.interfaces.ILoanApprovalModel;
import com.nord.persistence.loan.interfaces.ILoanModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for retrieving loans for approval
 * @author Samir Anwar Rana
 */

public class LoanApprovalModel implements ILoanApprovalModel {

    HashMap<Integer, Integer> loansEdit = new HashMap<>();

    @Override
    public List<ILoanModel> retrieveLoans() {
        List<ILoanModel> loanModelArrayList = new ArrayList<>();
        Connection connection = DbConnection.getConnection();

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("Select * from customer_loan where status = "+0+";");
            int i = 1;
            while (resultSet.next()){
                ILoanModel loanModel = new LoanModel();
                loanModel.setUserId(resultSet.getInt(ColumnNames.USER_ID));
                loanModel.setLoanType(resultSet.getString(ColumnNames.LOAN_TYPE));
                loanModel.setAmount(resultSet.getDouble(ColumnNames.AMOUNT));
                loanModel.setAppliedDate(resultSet.getDate(ColumnNames.LOAN_DATE));
                loanModel.setRepaymentDate(resultSet.getDate(ColumnNames.REPAYMENT_DATE));
                loanModel.setInterestRate(resultSet.getInt(ColumnNames.INTEREST_RATE));
                loanModel.setStatus(resultSet.getBoolean(ColumnNames.STATUS));
                loanModelArrayList.add(loanModel);
                int loan_id = resultSet.getInt(ColumnNames.LOAN_ID);
                loansEdit.put(i,loan_id);
                i++;
            }
            resultSet.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return loanModelArrayList;
    }

    @Override
    public boolean approveLoan(int loanId) {
        boolean result = false;
        Connection connection = DbConnection.getConnection();

        try {
            Statement statement = connection.createStatement();
            for (Map.Entry<Integer, Integer> entry : loansEdit.entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                if(key == loanId){
                    statement.executeUpdate("UPDATE customer_loan SET status = 1 WHERE loan_id = \""+value+"\";");
                }
            }
            result = true;

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return result;
    }
}
