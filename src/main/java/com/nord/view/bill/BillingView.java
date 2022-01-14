package com.nord.view.bill;

import com.nord.persistence.billing.interfaces.IBillingModel;
import com.nord.view.AbstractUserInterface;
import com.nord.view.bill.interfaces.IBillingView;
import com.nord.view.interfaces.IMenuInterface;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * This class implements all the methods that are displayed on the console for the
 * billing feature, it also extends the generic methods required for each feature
 * @author Samir Anwar Rana
 */

public class BillingView extends AbstractUserInterface implements IBillingView {

    @Override
    public void showMenu() {
        showHeader();
        System.out.println("1. Add a new bill payment");
        System.out.println("2. View/Delete a bill payment");
        System.out.println("3. Return to previous menu");
    }

    @Override
    public void showHeader() {
        System.out.println("=============================================================");
        System.out.println("                          Billing Page           ");
        System.out.println("=============================================================");

    }

    @Override
    public void showCycleOptions() {
        System.out.println("1. Daily");
        System.out.println("2. Weekly");
        System.out.println("3. Monthly");
        System.out.println("4. Yearly");
        System.out.println("5. None");

    }

    @Override
    public void showInvalidInput() {
        System.out.println("Please select a valid input");
    }

    @Override
    public Map<String, Object> addNewBilling(Map<String, Object> billDetails){
        System.out.println("Please Enter Billing Details: ");

        for(Map.Entry<String, Object> item : billDetails.entrySet()){
            System.out.println("Enter "+item.getKey()+" *");
            if(item.getKey().toLowerCase(Locale.ROOT).contains("date")){
                item.setValue(getDateInputLessThanToday());
            } else if(item.getKey().toLowerCase(Locale.ROOT).contains("cycle")){
                showCycleOptions();
                item.setValue(getIntegerInput());
            }  else if(item.getKey().toLowerCase(Locale.ROOT).contains("amount")){
                item.setValue(getDoubleInput());
            }
            else {
                item.setValue(getStringInputNoValidation());
            }
        }
        confirmDetails(billDetails);
        return billDetails;
    }

    @Override
    public void confirmDetails(Map<String, Object> billDetails){
        showHeader();
        String constants = "%-40s: %-20s%n";
        for(Map.Entry<String, Object> item : billDetails.entrySet()){
            if(item.getKey().contains("Cycle")){
                if(item.getValue().equals(1)){
                    System.out.printf(constants, item.getKey(), "Daily");
                } else if (item.getValue().equals(2)){
                    System.out.printf(constants, item.getKey(), "Weekly");
                } else if (item.getValue().equals(3)){
                    System.out.printf(constants, item.getKey(), "Monthly");
                } else if (item.getValue().equals(4)){
                    System.out.printf(constants, item.getKey(), "Yearly");
                }  else if (item.getValue().equals(5)){
                    System.out.printf(constants, item.getKey(), "None");
                }
            } else {
                System.out.printf(constants, item.getKey(), item.getValue());
            }
        }
        System.out.println("\n1. Confirm bill details");
        System.out.println("2. Back to main menu");
    }

    @Override
    public void accountAddedSuccessfully(){
        System.out.println("Bill payment created successfully ");
    }

    @Override
    public void billDeletedSuccessfully(){
        System.out.println("Bill deleted successfully ");
    }

    @Override
    public void showAllBilling(List<IBillingModel> billingModels) {
        showHeader();

        int count = 1;
        System.out.println("\nCurrent bill payments");
        for (IBillingModel bm : billingModels) {
            System.out.println(count++ + ". " + bm.getPayeeName()+ " - " + bm.getAcctNumber() + " - " +bm.getAmount() + " - " + bm.getPaymentDate() + " - " +bm.getPaymentInfo() + " - " + bm.getPaymentCycle());
        }
        System.out.println("=============================================================");
        System.out.println("\n0. Back to main menu");
        System.out.println("Enter the name of the payee you want to delete");
    }
}
