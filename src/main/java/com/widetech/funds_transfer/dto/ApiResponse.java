package com.widetech.funds_transfer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {

  private Object data;
}
