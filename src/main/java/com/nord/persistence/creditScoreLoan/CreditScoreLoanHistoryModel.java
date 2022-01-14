package com.nord.persistence.creditScoreLoan;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;
import com.nord.persistence.creditScoreLoan.interfaces.ICreditScoreLoanHistoryModel;
import com.nord.persistence.creditScoreLoan.interfaces.ICreditScoreLoanModel;
import com.nord.service.Context;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles previous history of the user who has taken the loan with the database
 * @author Harit Patwa
 */
public class CreditScoreLoanHistoryModel implements ICreditScoreLoanHistoryModel {


  public List<ICreditScoreLoanModel> getCreditScoreLoanHistory() {
    List<ICreditScoreLoanModel> cslm = new ArrayList<>();
    String query = "SELECT * FROM customer_crs where user_id = " + Context.getLoggedInUserId() + ";";
    Statement statement = null;
    try {
      Connection dbConnection = DbConnection.getConnection();
      statement = dbConnection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while (resultSet.next()) {
        CreditScoreLoanModel clm = new CreditScoreLoanModel();
        clm.setAmount(resultSet.getInt(ColumnNames.AMOUNT));
        clm.setDueDate(resultSet.getDate(ColumnNames.DUE_DATE));
        clm.setPaid(resultSet.getBoolean(ColumnNames.IS_PAID));
        cslm.add(clm);
      }

      resultSet.close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    DbConnection.closeDbConnection();
    return cslm;
  }
}
