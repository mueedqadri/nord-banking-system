package com.nord.persistence.creditScoreLoan;

import com.nord.common.ColumnNames;
import com.nord.persistence.DbConnection;
import com.nord.persistence.creditScoreLoan.interfaces.ICreditScore;
import com.nord.service.Context;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class handles credit score of the user and executes functions with database such as getting crs score, setting crs score with the database
 * inserting crs score
 * @author Harit Patwa
 */

public class CreditScore implements ICreditScore {
    private int crsScore;
    @Override
    public int getCrsScore()
    {
        String query = "SELECT crs_score FROM customer_crs_score where user_id = "+Context.getLoggedInUserId()+";";
        Statement statement = null;
        try {
            Connection dbConnection = DbConnection.getConnection();
            statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            this.crsScore = -1;
            while (resultSet.next()) {
                this.crsScore = resultSet.getInt(ColumnNames.CRS_SCORE);
            }
            resultSet.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DbConnection.closeDbConnection();
        return this.crsScore;
    }
    @Override
    public void setCrsScore(int score)
    {

        String query = "UPDATE customer_crs_score\n" +
                "SET crs_score="+score+"\n" +
                "WHERE user_id = "+Context.getLoggedInUserId()+";";
        Statement statement = null;

        Connection dbConnection = DbConnection.getConnection();
        try {
            statement = dbConnection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DbConnection.closeDbConnection();
    }
    @Override
    public void insertCrsScore()
    {

        String query = "INSERT INTO customer_crs_score(user_id,crs_score)\n" +
                "VALUES ("+Context.getLoggedInUserId()+",600);";
        Statement statement = null;

        Connection dbConnection = DbConnection.getConnection();
        try {
            statement = dbConnection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DbConnection.closeDbConnection();
    }

}
