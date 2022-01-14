package com.nord.persistence.fixedDeposit;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;
import com.nord.persistence.bankingTransactions.AbstractBanking;
import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlanPersistence;
import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlansModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Persistence for handling the FD plans
 * @author Abdul Mueed Qadri
 */
public class FixedDepositPlanPersistence extends AbstractBanking implements IFixedDepositPlanPersistence {
    @Override
    public List<IFixedDepositPlansModel> getFds() {
        List<IFixedDepositPlansModel> fixedDepositPlansList = new ArrayList<>();
        try {
            Connection dbConnection = DbConnection.getConnection();
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM fixed_deposit_plans ;");
            while (resultSet.next()) {
                IFixedDepositPlansModel fixedDepositPlansModel = new FixedDepositPlansModel();
                fixedDepositPlansModel.setId(resultSet.getInt(ColumnNames.PLAN_ID));
                fixedDepositPlansModel.setMinDuration(resultSet.getInt(ColumnNames.MIN_DURATION));
                fixedDepositPlansModel.setMaxDuration(resultSet.getInt(ColumnNames.MAX_DURATION));
                fixedDepositPlansModel.setInterest(resultSet.getDouble(ColumnNames.INTEREST_RATE));
                fixedDepositPlansModel.setInterestSeniors(resultSet.getDouble(ColumnNames.INTEREST_RATE_SENIORS));
                fixedDepositPlansModel.setType(resultSet.getString(ColumnNames.PLAN_DURATION));
                fixedDepositPlansList.add(fixedDepositPlansModel);
            }
            resultSet.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return fixedDepositPlansList;
    }
}
