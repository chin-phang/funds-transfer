package com.widetech.funds_transfer.service.impl;

import com.widetech.funds_transfer.dto.AccountInfo;
import com.widetech.funds_transfer.entity.Account;
import com.widetech.funds_transfer.entity.User;
import com.widetech.funds_transfer.exception.UserNotLoginException;
import com.widetech.funds_transfer.mapper.AccountInfoMapper;
import com.widetech.funds_transfer.repository.AccountRepository;
import com.widetech.funds_transfer.repository.UserRepository;
import com.widetech.funds_transfer.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

  private final UserRepository userRepository;

  private final AccountRepository accountRepository;

  private final AccountInfoMapper accountInfoMapper;

  @Override
  public List<AccountInfo> findAllByUserId(Long userId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
      throw new UserNotLoginException();
    }

    User loginUser = (User) authentication.getPrincipal();

    boolean validUserId = userRepository.existsById(userId);

    if (!validUserId) {
      throw new IllegalArgumentException("Invalid user ID.");
    }

    if (!loginUser.getId().equals(userId)) {
      log.error("User {} not allowed to view with userId {}", loginUser.getUsername(), userId);
      throw new IllegalArgumentException("Invalid user ID.");
    }

    List<Account> accounts = accountRepository.findAllByUserId(loginUser.getId());

    return accounts.stream().map(accountInfoMapper::toDto).toList();
  }
}
