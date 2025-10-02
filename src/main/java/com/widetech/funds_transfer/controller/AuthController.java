package com.widetech.funds_transfer.controller;

import com.widetech.funds_transfer.dto.LoginRequest;
import com.widetech.funds_transfer.dto.LoginResponse;
import com.widetech.funds_transfer.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthServiceImpl authServiceImpl;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginReq) {
    return ResponseEntity.ok(authServiceImpl.authenticate(loginReq));
  }

  @PostMapping("/logout")
  public void logout() {
    authServiceImpl.logout();
  }
}
