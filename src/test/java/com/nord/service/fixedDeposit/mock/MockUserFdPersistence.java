package com.nord.service.fixedDeposit.mock;

import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositsPersistence;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock persistence to mock the persistence to handle the user FDs
 * @author Abdul Mueed Qadri
 */
public class MockUserFdPersistence implements IUserFixedDepositsPersistence {
  public List<IUserFixedDepositModel> fds = new ArrayList<>();

  public MockUserFdPersistence (IUserFixedDepositModel fd){
    fds.add(fd);
  }

  @Override
  public boolean addFdToUser(IUserFixedDepositModel fd) {
    fds.add(fd);
    return true;
  }

  @Override
  public List<IUserFixedDepositModel> getAllFdsForUser() {
    return fds;
  }

  @Override
  public boolean withdrawFd(int fdId, double amount) {
    fds.remove(0);
    return true;
  }
}
