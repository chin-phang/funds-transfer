package com.widetech.funds_transfer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

  private String accessToken;
}
