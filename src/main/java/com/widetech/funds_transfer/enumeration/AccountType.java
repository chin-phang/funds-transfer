package com.widetech.funds_transfer.enumeration;

import lombok.Getter;

@Getter
public enum AccountType {

  SAVINGS {
    @Override
    public boolean isSavings() {
      return true;
    }
  },
  CURRENT {
    @Override
    public boolean isCurrent() {
      return true;
    }
  };

  public boolean isSavings() {
    return false;
  }

  public boolean isCurrent() {
    return false;
  }
}
