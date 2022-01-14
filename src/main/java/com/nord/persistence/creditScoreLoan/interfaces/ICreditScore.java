package com.nord.persistence.creditScoreLoan.interfaces;

public interface ICreditScore {

    /**
     * sets the credit rating score of the customer
     * @param score
     */
    void setCrsScore(int score);
    /**
     *gets the credit rating score of the customer
     * @return
     */
    int getCrsScore();
    /**
     * Inserts the default credit rating score to the customer
     */
    void insertCrsScore();
}
