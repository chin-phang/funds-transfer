package com.widetech.funds_transfer.exception;

public class AccountNotFoundException extends RuntimeException {
  public AccountNotFoundException(Long userId) {
    super("Account not found for userId: " + userId);
  }
}
