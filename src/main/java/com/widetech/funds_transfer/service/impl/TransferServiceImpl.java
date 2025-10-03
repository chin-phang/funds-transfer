package com.widetech.funds_transfer.service.impl;

import com.widetech.funds_transfer.dto.TransferRequest;
import com.widetech.funds_transfer.entity.Transfer;
import com.widetech.funds_transfer.enumeration.TransferStatus;
import com.widetech.funds_transfer.repository.TransferRepository;
import com.widetech.funds_transfer.service.AccountService;
import com.widetech.funds_transfer.service.TransferService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

  private static final Logger log = LoggerFactory.getLogger(TransferServiceImpl.class);

  private final TransferRepository transferRepository;

  private final AccountService accountService;

  private static final BigDecimal DAILY_LIMIT_IDR = new BigDecimal("50000000");

  /*@Transactional
  public Transfer processTransfer(TransferRequest request) {
    // 1. Cannot transfer to same account
    if (request.getSourceAccount().equals(request.getDestinationAccount())) {
      throw new IllegalArgumentException("Cannot transfer to the same account");
    }

    // 2. Check sufficient balance
    BigDecimal sourceBalance = accountService.getBalance(request.getSourceAccount(), request.getCurrency());
    if (sourceBalance.compareTo(request.getAmount()) < 0) {
      throw new IllegalArgumentException("Insufficient balance");
    }

    // 3. Check daily limit (only for IDR)
    if ("IDR".equalsIgnoreCase(request.getCurrency())) {
      LocalDateTime todayStart = LocalDate.now().atStartOfDay();
      BigDecimal todayTotal = transferRepository.getDailyTotalTransfers(
          request.getSourceAccount(),
          request.getCurrency(),
          todayStart
      );

      if (todayTotal.add(request.getAmount()).compareTo(DAILY_LIMIT_IDR) > 0) {
        throw new IllegalArgumentException("Daily transfer limit exceeded (50,000,000 IDR)");
      }
    }

    // 4. Create transfer (PENDING first)
    Transfer transfer = Transfer.builder()
        .sourceAccount(request.getSourceAccount())
        .destinationAccount(request.getDestinationAccount())
        .amount(request.getAmount())
        .currency(request.getCurrency())
        .status(TransferStatus.PENDING)
        .timestamp(LocalDateTime.now())
        .build();

    transfer = transferRepository.save(transfer);

    try {
      // Deduct + Credit (your AccountService handles this safely with @Transactional)
      accountService.debit(request.getSourceAccount(), request.getAmount(), request.getCurrency());
      accountService.credit(request.getDestinationAccount(), request.getAmount(), request.getCurrency());

      transfer.setStatus(TransferStatus.SUCCESS);
    } catch (Exception e) {
      transfer.setStatus(TransferStatus.FAILED);
    }

    return transferRepository.save(transfer);
  }*/
}

