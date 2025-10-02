package com.widetech.funds_transfer.service;

import com.widetech.funds_transfer.dto.AccountInfo;

import java.util.List;

public interface AccountService {

  List<AccountInfo> findAllByUserId(Long userId);
}
