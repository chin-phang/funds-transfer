package com.widetech.funds_transfer.enumeration;

import lombok.Getter;

@Getter
public enum AccountStatus {

  ACTIVE {
    @Override
    public boolean isActive() {
      return true;
    }
  },
  INACTIVE {
    @Override
    public boolean isInactive() {
      return true;
    }
  };

  public boolean isActive() {
    return false;
  }

  public boolean isInactive() {
    return false;
  }
}
