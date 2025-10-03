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

  private BigDecimal balance;

}
