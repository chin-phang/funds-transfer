package com.widetech.funds_transfer.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class TransferInfo {

  private String refNo;

  private String sourceAccount;

  private String destinationAccount;

  private BigDecimal transferAmount;

  private String currency;

  private String transferType;

  private String transferStatus;

  private Instant transferDate;
}
