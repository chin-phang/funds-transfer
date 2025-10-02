package com.widetech.funds_transfer.mapper;

import com.widetech.funds_transfer.dto.AccountInfo;
import com.widetech.funds_transfer.entity.Account;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccountInfoMapper {

  @Mapping(target = "accountNumber", source = "accountNo")
  @Mapping(target = "accountType", source = "type")
  AccountInfo toDto(Account entity);

  Account toEntity(AccountInfo dto);
}
