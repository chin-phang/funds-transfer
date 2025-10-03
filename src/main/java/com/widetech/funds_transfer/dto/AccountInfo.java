package com.widetech.funds_transfer.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountInfo {

  private String accountNumber;

  private String accountType;

  private String currency;

  private BigDecimal balance;

}
