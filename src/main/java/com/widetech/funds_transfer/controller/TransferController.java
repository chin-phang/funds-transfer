package com.widetech.funds_transfer.controller;

import com.widetech.funds_transfer.dto.ApiResponse;
import com.widetech.funds_transfer.dto.TransferRequest;
import com.widetech.funds_transfer.entity.Transfer;
import com.widetech.funds_transfer.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transfer")
public class TransferController {

  private final TransferService transferService;

  @PostMapping
  public ResponseEntity<ApiResponse> createTransfer(@RequestBody TransferRequest request) {
    //Transfer transfer = transferService.processTransfer(request);

    return ResponseEntity.ok(ApiResponse.builder().build());
  }
}
