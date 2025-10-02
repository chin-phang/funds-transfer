package com.widetech.funds_transfer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.widetech.funds_transfer.dto.LoginRequest;
import com.widetech.funds_transfer.dto.LoginResponse;
import com.widetech.funds_transfer.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

  private MockMvc mockMvc;

  @Mock
  private AuthService authService;

  @InjectMocks
  private AuthController authController;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
  }

  @Test
  void login_ValidCredentials_ReturnsToken() throws Exception {
    String username = "testuser";
    String password = "testpass";
    String accessToken = "test.token.123";

    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setUsername(username);
    loginRequest.setPassword(password);

    LoginResponse loginResponse = LoginResponse.builder()
        .accessToken(accessToken)
        .build();

    when(authService.authenticate(any(LoginRequest.class))).thenReturn(loginResponse);

    mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginRequest)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.accessToken").value(accessToken));
  }

  @Test
  void login_MissingUsername_ReturnsBadRequest() throws Exception {
    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setPassword("testpass");

    mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginRequest)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void login_MissingPassword_ReturnsBadRequest() throws Exception {
    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setUsername("testuser");

    mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginRequest)))
        .andExpect(status().isBadRequest());
  }
}