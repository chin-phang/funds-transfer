package com.widetech.funds_transfer.controller;

import com.widetech.funds_transfer.dto.AccountInfo;
import com.widetech.funds_transfer.dto.ApiResponse;
import com.widetech.funds_transfer.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

  private final AccountService accountService;

  @GetMapping("/{userId}")
  public ResponseEntity<ApiResponse> findAllByUserId(@PathVariable Long userId) {
    List<AccountInfo> result = accountService.findAllByUserId(userId);

    ApiResponse response = ApiResponse.builder()
        .data(result)
        .build();

    return ResponseEntity.ok(response);
  }
}
