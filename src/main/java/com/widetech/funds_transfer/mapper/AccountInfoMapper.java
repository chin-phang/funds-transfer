package com.widetech.funds_transfer.mapper;

import com.widetech.funds_transfer.dto.AccountInfo;
import com.widetech.funds_transfer.entity.Account;
import com.widetech.funds_transfer.enumeration.Currency;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccountInfoMapper {

  @Mapping(target = "accountNumber", source = "accountNo")
  @Mapping(target = "accountType", source = "type")
  @Mapping(target = "balance", ignore = true)
  AccountInfo toDto(Account entity);

  Account toEntity(AccountInfo dto);

  @AfterMapping
  default void adjustBalance(Account entity, @MappingTarget AccountInfo dto) {
    if (entity.getBalance() != null) {
      int scale = dto.getCurrency() != null ?
          getCurrencyScale(dto.getCurrency()) : 2;
      BigDecimal displayBalance = BigDecimal.valueOf(entity.getBalance(), scale);
      dto.setBalance(displayBalance);
    }
  }

  private int getCurrencyScale(String currency) {
    return Currency.valueOf(currency).getScale();
  }
}
