package com.widetech.funds_transfer.mapper;

import com.widetech.funds_transfer.dto.TransferInfo;
import com.widetech.funds_transfer.entity.Transfer;
import com.widetech.funds_transfer.enumeration.Currency;
import org.mapstruct.*;

import java.math.BigDecimal;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TransferInfoMapper {

  @Mapping(target = "transferDate", source = "date")
  @Mapping(target = "transferType", source = "type")
  @Mapping(target = "sourceAccount", source = "fromAccount")
  @Mapping(target = "destinationAccount", source = "toAccount")
  @Mapping(target = "transferStatus", source = "status")
  @Mapping(target = "transferAmount", ignore = true)
  TransferInfo toDto(Transfer entity);

  Transfer toEntity(TransferInfo dto);

  @AfterMapping
  default void adjustAmount(Transfer entity, @MappingTarget TransferInfo dto) {
    if (entity.getAmount() != null) {
      int scale = dto.getCurrency() != null ?
          getCurrencyScale(dto.getCurrency()) : 2;
      BigDecimal displayAmount = BigDecimal.valueOf(entity.getAmount(), scale);
      dto.setTransferAmount(displayAmount);
    }
  }

  private int getCurrencyScale(String currency) {
    return Currency.valueOf(currency).getScale();
  }
}
