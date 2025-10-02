package com.widetech.funds_transfer.dto;

import com.widetech.funds_transfer.enumeration.Currency;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@Builder
public class AccountInfo {

  private String accountNumber;

  private String accountType;

  private String currency;

  @Setter(AccessLevel.NONE)
  private BigDecimal balance;

  public void setBalance(BigDecimal balance) {
    if (balance == null) {
      this.balance = null;
      return;
    }

    this.balance = balance.setScale(Currency.valueOf(this.currency).getScale(), RoundingMode.HALF_UP);
  }

  private int getCurrencyScale(String currency) {
    if (currency == null) {
      return 2;
    }

    return Currency.valueOf(currency).getScale();
  }
}
