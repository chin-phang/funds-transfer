package com.widetech.funds_transfer.service.impl;

import com.widetech.funds_transfer.dto.LoginRequest;
import com.widetech.funds_transfer.dto.LoginResponse;
import com.widetech.funds_transfer.entity.User;
import com.widetech.funds_transfer.entityGraphPath.UserEntityGraphPath;
import com.widetech.funds_transfer.jwt.JwtTokenProvider;
import com.widetech.funds_transfer.repository.UserRepository;
import com.widetech.funds_transfer.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;

  private final AuthenticationManager authenticationManager;

  private final JwtTokenProvider jwtTokenProvider;

  public LoginResponse authenticate(LoginRequest loginReq) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));

    User user = userRepository.findByUsername(loginReq.getUsername(), UserEntityGraphPath.UserWithRoles.getEntityGraph())
        .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));

    String jwtToken = jwtTokenProvider.generateToken(user);

    return LoginResponse.builder()
        .accessToken(jwtToken)
        .build();
  }

  public void logout() {

  }
}
