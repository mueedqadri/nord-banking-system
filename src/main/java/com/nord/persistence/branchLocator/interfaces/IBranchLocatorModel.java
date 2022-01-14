package com.nord.persistence.branchLocator.interfaces;

/**
 * This is a class contains abstract methods for BranchLocatorModel class
 * @author Jay Nimeshkumar Patel
 */
public interface IBranchLocatorModel {

  String getLocation();

  void setLocation(String location);

  String getIfsc();

  void setIfsc(String ifsc);

  String getCityId();

  void setCityId(String id);

  String getCityName();

  void setCityName(String name);
}
