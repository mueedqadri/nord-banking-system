package com.nord.persistence.creditCardServicesAndRecommendation;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;
import com.nord.persistence.creditCardServicesAndRecommendation.interfaces.ICreditCardBills;
import com.nord.service.Context;
import com.nord.persistence.creditCardServicesAndRecommendation.interfaces.ICreditCardDetails;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles billing mechanism with the database, it retrieves the bills and pays the bill
 * @author Harit Patwa
 */

public class CreditCardBills implements ICreditCardBills {

  @Override
  public List<ICreditCardDetails> getBills() {
    List<ICreditCardDetails> billList = new ArrayList<>();
    try {
      Connection dbConnection = DbConnection.getConnection();
      Statement statement = dbConnection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM customer_credit_card_bill where user_id = "+ Context.getLoggedInUserId()+";");
      while (resultSet.next()) {
        if(!resultSet.getBoolean(ColumnNames.IS_PAID)) {
          ICreditCardDetails creditBills = new CreditCardDetails();
          creditBills.setCcbId(resultSet.getInt(ColumnNames.CCB_ID));
          creditBills.setAmount(resultSet.getInt(ColumnNames.AMOUNT));
          creditBills.setPaid(resultSet.getBoolean(ColumnNames.IS_PAID));
          creditBills.setDate(resultSet.getDate(ColumnNames.DUE_DATE));
          billList.add(creditBills);
        }
      }
      resultSet.close();
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    DbConnection.closeDbConnection();
    return billList;
  }
  @Override
  public boolean payBill(int creditCardBillId) {
    String query = "UPDATE customer_credit_card_bill\n" +
            "SET isPaid =true\n" +
            "where ccb_id = "+creditCardBillId+";";
    Statement statement = null;

    Connection dbConnection = DbConnection.getConnection();
    try {
      statement = dbConnection.createStatement();
      statement.executeUpdate(query);
    } catch (SQLException throwables) {
      return false;
    }
    DbConnection.closeDbConnection();
    return true;
  }
}
