package com.widetech.funds_transfer.enumeration;

import lombok.Getter;

@Getter
public enum TransferStatus {

  PENDING {
    @Override
    public boolean isPending() {
      return true;
    }
  },
  SUCCESS {
    @Override
    public boolean isSuccess() {
      return true;
    }
  },
  FAILED {
    @Override
    public boolean isFailed() {
      return true;
    }
  };

  public boolean isPending() {
    return false;
  }

  public boolean isSuccess() {
    return false;
  }

  public boolean isFailed() {
    return false;
  }
}
