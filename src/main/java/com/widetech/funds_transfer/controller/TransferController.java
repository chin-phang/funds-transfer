package com.widetech.funds_transfer.controller;

import com.widetech.funds_transfer.dto.ApiResponse;
import com.widetech.funds_transfer.dto.SearchTransferRequest;
import com.widetech.funds_transfer.dto.TransferInfo;
import com.widetech.funds_transfer.dto.TransferRequest;
import com.widetech.funds_transfer.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transfers")
public class TransferController {

  private final TransferService transferService;

  @PostMapping
  public ResponseEntity<ApiResponse> createTransfer(@RequestBody TransferRequest request) {
    //Transfer transfer = transferService.processTransfer(request);

    return ResponseEntity.ok(ApiResponse.builder().build());
  }

  @GetMapping
  public ResponseEntity<ApiResponse> findAll(@ModelAttribute SearchTransferRequest searchTransferReq,
      @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
    Page<TransferInfo> result = transferService.findAll(searchTransferReq, PageRequest.of(pageNo, pageSize));

    ApiResponse response = ApiResponse.builder()
        .data(new PagedModel<>(result))
        .build();

    return ResponseEntity.ok(response);
  }
}
