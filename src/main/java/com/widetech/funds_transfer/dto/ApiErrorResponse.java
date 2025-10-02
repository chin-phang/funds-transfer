package com.widetech.funds_transfer.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiErrorResponse {

  private String error;

  private String message;

  private LocalDateTime timestamp;
}
