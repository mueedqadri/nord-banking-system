package com.nord.service.fixedDeposit;

import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlansModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.service.fixedDeposit.interfaces.*;
import com.nord.view.fixedDeposit.intrefaces.IFixedDepositMainView;

import java.util.List;

/**
 * Shows the confirmation of the FD received
 * @author Mueed Qadri
 */
public class CreateFixedDeposit implements ICreateFixedDeposit {
  private IFixedDepositFactory factory;

  public CreateFixedDeposit setFactory(IFixedDepositFactory factory) {
    this.factory = factory;
    return this;
  }

  @Override
  public IUserFixedDepositModel create(IGetFixedDeposit fdType, List<IFixedDepositPlansModel> fdPlans, double customerBalance, IFixedDepositMainView view){
    IUserFixedDepositModel selectedFd =  fdType.getFixedDeposit(fdPlans);
    if(selectedFd != null){
      if(selectedFd.getAmount()> customerBalance){
        view.notEnoughBalance();
      } else{
        IFixedDepositConfirmation confirmation = factory.createFdConfirmation(selectedFd, fdPlans);
        confirmation.fdConfirmation();
      }
    }
    return selectedFd;
  }
}
