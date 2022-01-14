package com.nord.view.bill.interfaces;

import com.nord.persistence.billing.interfaces.IBillingModel;
import com.nord.view.interfaces.IUserInterface;

import java.util.List;
import java.util.Map;

public interface IBillingView extends IUserInterface {
    void showMenu();

    void showHeader();

    void showCycleOptions();

    void showInvalidInput();

    Map<String, Object> addNewBilling(Map<String, Object> billDetails);

    void confirmDetails(Map<String, Object> billDetails);

    void accountAddedSuccessfully();

    void billDeletedSuccessfully();

    void showAllBilling(List<IBillingModel> billingModels);
}
