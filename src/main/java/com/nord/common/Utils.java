package com.nord.common;

import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;

import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * Common util methods used across multiple methods
 * @author Abdul Mueed Qadri
 */
public class Utils {

    private static double REWARD_FOR_FD = .0001 ;
    private static double CONVERSION_RATE = .05;

    public static String encryptToSha256(final String base){
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            System.out.println("Couldn't create SHA256 Hash");
            System.exit(0);
            return "" ;
        }
    }

    public static int calculateRewardPointFd(IUserFixedDepositModel fd){
        int fdDurationInDays = (int ) ChronoUnit.DAYS.between(LocalDate.now(),
                fd.getWithdrawalDate());
        return (int) ((int ) fd.getAmount() * fdDurationInDays * REWARD_FOR_FD);
    }

    public static String daysConvertToString(int noOfDays) {
        Period period = Period.between(LocalDate.now(),
                LocalDate.now().plusDays(noOfDays));
        StringBuilder result = new StringBuilder();
        if (period.getYears() > 0) {
            result.append(period.getYears());
            result.append(hasMoreThanOne(period.getYears(), " year"));
        }
        if (period.getMonths() > 0) {
            String hasAndMonth = period.getYears() > 0 ? "and " : "";
            result.append(hasAndMonth);
            result.append(period.getMonths());
            result.append(hasMoreThanOne(period.getMonths(), " month"));
        }
        if (period.getDays() > 0) {
            String hasAndDay = period.getMonths() > 1 ? "and " : "";
            result.append(hasAndDay).append(period.getDays());
            result.append(hasMoreThanOne(period.getDays(), " day"));
        }
        return result.toString();
    }

    private static String hasMoreThanOne(int items, String itemName) {
        if (items > 1) {
            return itemName + "s ";
        } else {
            return itemName + " ";
        }
    }

    public static double calculateProfit(double interest, double amount) {
        return amount * (interest / 100);
    }

    public static double rewardToDollar(long points){
        return points * CONVERSION_RATE;
    }
}
