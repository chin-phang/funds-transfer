package com.widetech.funds_transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {

  private String createdBy;

  private Instant createdAt;

  private String lastModifiedBy;

  private Instant lastModifiedAt;
}
