package com.widetech.funds_transfer.enumeration;

import lombok.Getter;

@Getter
public enum TransferType {

  FUNDS_TRANSFER {
    @Override
    public boolean isFundsTransfer() {
      return true;
    }
  },
  CREDIT_CARD_PAYMENT {
    @Override
    public boolean isCreditCardPayment() {
      return true;
    }
  },
  LOAN_FINANCING_PAYMENT {
    @Override
    public boolean isLoanFinancingPayment() {
      return true;
    }
  };

  public boolean isFundsTransfer() {
    return false;
  }

  public boolean isCreditCardPayment() {
    return false;
  }

  public boolean isLoanFinancingPayment() {
    return false;
  }
}
