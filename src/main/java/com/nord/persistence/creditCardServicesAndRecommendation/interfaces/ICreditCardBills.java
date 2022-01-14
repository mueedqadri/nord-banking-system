package com.nord.persistence.creditCardServicesAndRecommendation.interfaces;

import java.util.List;

public interface ICreditCardBills {
   List<ICreditCardDetails>  getBills();

   boolean payBill(int ccb_id);
}
