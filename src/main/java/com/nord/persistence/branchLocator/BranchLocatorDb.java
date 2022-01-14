package com.nord.persistence.branchLocator;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;
import com.nord.persistence.branchLocator.interfaces.IBranchLocatorDb;
import com.nord.persistence.branchLocator.interfaces.IBranchLocatorModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for updating and fetching withdraw and spending limits
 * @author Jay Nimeshkumar Patel
 */
public class BranchLocatorDb implements IBranchLocatorDb {

  @Override
  public List<IBranchLocatorModel> getCities() {
    List<IBranchLocatorModel> cityList = new ArrayList<>();
    try {
      Connection connection = DbConnection.getConnection();
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT distinct city_id, city from bank_branches order by city_id asc;");
      while (resultSet.next()) {
        IBranchLocatorModel model = new BranchLocatorModel();
        model.setCityId(resultSet.getString(ColumnNames.CITY_ID));
        model.setCityName(resultSet.getString(ColumnNames.CITY));
        cityList.add(model);
      }
      System.out.println();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return cityList;
  }

  @Override
  public List<IBranchLocatorModel> locateBranch(int userInput) {
    List<IBranchLocatorModel> branchList = new ArrayList<>();
    try {
      Connection connection = DbConnection.getConnection();
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM bank_branches where city_id = " + userInput + ";");
      while (resultSet.next()) {
        IBranchLocatorModel model = new BranchLocatorModel();
        model.setIfsc(resultSet.getString(ColumnNames.IFSC));
        model.setLocation(resultSet.getString(ColumnNames.LOCATION));
        branchList.add(model);
      }
      System.out.println();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return branchList;
  }
}
