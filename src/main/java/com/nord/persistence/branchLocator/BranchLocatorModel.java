package com.nord.persistence.branchLocator;

import com.nord.persistence.branchLocator.interfaces.IBranchLocatorModel;

/**
 * This is a Model class representing branch locator
 * @author Jay Nimeshkumar Patel
 */
public class BranchLocatorModel implements IBranchLocatorModel {

  private String location;
  private String ifsc;
  private String id;
  private String city;

  @Override
  public String getLocation() {
    return location;
  }

  @Override
  public void setLocation(String location) {
    this.location = location;
  }

  @Override
  public String getIfsc() {
    return ifsc;
  }

  @Override
  public void setIfsc(String ifsc) {
    this.ifsc = ifsc;
  }

  @Override
  public String getCityId() {
    return id;
  }

  @Override
  public void setCityId(String id) {
    this.id = id;
  }

  @Override
  public String getCityName() {
    return city;
  }

  @Override
  public void setCityName(String city) {
    this.city = city;
  }
}
