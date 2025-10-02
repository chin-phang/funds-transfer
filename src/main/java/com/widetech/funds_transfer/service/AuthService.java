package com.widetech.funds_transfer.service;

import com.widetech.funds_transfer.dto.LoginRequest;
import com.widetech.funds_transfer.dto.LoginResponse;

public interface AuthService {

  LoginResponse authenticate(LoginRequest loginReq);
}
