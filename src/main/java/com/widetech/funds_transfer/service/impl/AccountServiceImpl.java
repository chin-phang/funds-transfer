package com.widetech.funds_transfer.service.impl;

import com.widetech.funds_transfer.dto.AccountInfo;
import com.widetech.funds_transfer.entity.Account;
import com.widetech.funds_transfer.entity.User;
import com.widetech.funds_transfer.exception.AccountNotFoundException;
import com.widetech.funds_transfer.mapper.AccountInfoMapper;
import com.widetech.funds_transfer.repository.AccountRepository;
import com.widetech.funds_transfer.repository.UserRepository;
import com.widetech.funds_transfer.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final UserRepository userRepository;

  private final AccountRepository accountRepository;

  private final AccountInfoMapper accountInfoMapper;

  @Override
  public List<AccountInfo> findAllByUserId(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new AccountNotFoundException(userId));

    List<Account> accounts = accountRepository.findAllByUserId(user.getId());

    return accounts.stream().map(accountInfoMapper::toDto).toList();
  }
}
