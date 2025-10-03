package com.widetech.funds_transfer.exception;

public class UserNotLoginException extends RuntimeException {
  public UserNotLoginException() {
    super("User not login.");
  }
}
