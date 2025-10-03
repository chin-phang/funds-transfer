package com.widetech.funds_transfer.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {

  private String sourceAccount;

  private String destinationAccount;

  private BigDecimal amount;

  private String currency;
}
