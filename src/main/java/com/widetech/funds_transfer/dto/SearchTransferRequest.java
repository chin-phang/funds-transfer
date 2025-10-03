package com.widetech.funds_transfer.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SearchTransferRequest {

  private String accountNumber;

  private String refNo;

  private String transferType;

  private String transferStatus;

  private String currency;

  private BigDecimal amountMin;

  private BigDecimal amountMax;

  private LocalDate transferDate;
}
