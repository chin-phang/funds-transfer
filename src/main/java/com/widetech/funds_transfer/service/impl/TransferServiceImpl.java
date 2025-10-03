package com.widetech.funds_transfer.service.impl;

import com.widetech.funds_transfer.dto.SearchTransferRequest;
import com.widetech.funds_transfer.dto.TransferInfo;
import com.widetech.funds_transfer.entity.Transfer;
import com.widetech.funds_transfer.enumeration.Currency;
import com.widetech.funds_transfer.mapper.TransferInfoMapper;
import com.widetech.funds_transfer.repository.TransferRepository;
import com.widetech.funds_transfer.service.AccountService;
import com.widetech.funds_transfer.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static com.widetech.funds_transfer.specification.TransferSpec.*;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

  private static final Logger log = LoggerFactory.getLogger(TransferServiceImpl.class);

  private static final ZoneId ZONE_ID = ZoneId.of("Asia/Kuala_Lumpur");

  private final TransferRepository transferRepository;

  private final TransferInfoMapper transferInfoMapper;

  private final AccountService accountService;

  private static final BigDecimal DAILY_LIMIT_IDR = new BigDecimal("50000000");

  @Override
  public Page<TransferInfo> findAll(SearchTransferRequest searchTransferReq, Pageable pageable) {
    Specification<Transfer> transferSpec = createTransferSpec(searchTransferReq);

    Page<Transfer> transferPage = transferRepository.findAll(transferSpec, pageable);

    List<TransferInfo> result = new ArrayList<>();

    if (CollectionUtils.isNotEmpty(transferPage.getContent())) {
      for (Transfer transfer: transferPage.getContent()) {
        TransferInfo transferInfo = TransferInfo.builder()
            .refNo(transfer.getRefNo())
            .sourceAccount(transfer.getFromAccount())
            .destinationAccount(transfer.getToAccount())
            .transferType(transfer.getType().name())
            .transferAmount(BigDecimal.valueOf(transfer.getAmount(), getCurrencyScale(transfer.getCurrency().getCode())))
            .currency(transfer.getCurrency().getCode())
            .transferStatus(transfer.getStatus().name())
            .transferDate(transfer.getDate())
            .build();

        result.add(transferInfo);
      }
    }

    return new PageImpl<>(result, pageable, transferPage.getTotalElements());
  }

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

  private Specification<Transfer> createTransferSpec(SearchTransferRequest searchTransferReq) {
    Specification<Transfer> specs = (root, query, cb) -> cb.conjunction();

    if (StringUtils.isNotBlank(searchTransferReq.getRefNo())) {
      specs = specs.and(refNoEqual(searchTransferReq.getRefNo().trim()));
    }

    if (StringUtils.isNotBlank(searchTransferReq.getAccountNumber())) {
      specs = specs.and(fromAccountEqual(searchTransferReq.getAccountNumber().trim()));
      specs = specs.or(toAccountEqual(searchTransferReq.getAccountNumber().trim()));
    }

    if (StringUtils.isNotBlank(searchTransferReq.getTransferStatus())) {
      specs = specs.and(transferStatusEqual(searchTransferReq.getTransferStatus().trim()));
    }

    if (StringUtils.isNotBlank(searchTransferReq.getTransferType())) {
      specs = specs.and(transferTypeEqual(searchTransferReq.getTransferType().trim()));
    }

    if (StringUtils.isNotBlank(searchTransferReq.getCurrency())) {
      specs = specs.and(currencyEqual(searchTransferReq.getCurrency().trim()));
    }

    if (searchTransferReq.getAmountMin() != null) {
      int scale = searchTransferReq.getCurrency() != null ? getCurrencyScale(searchTransferReq.getCurrency()) : 2;
      Long longAmtMin = searchTransferReq.getAmountMin().movePointRight(scale).longValue();
      specs = specs.and(transferAmountGreaterThanEqual(longAmtMin));
    }

    if (searchTransferReq.getAmountMax() != null) {
      int scale = searchTransferReq.getCurrency() != null ? getCurrencyScale(searchTransferReq.getCurrency()) : 2;
      Long longAmtMax = searchTransferReq.getAmountMax().movePointRight(scale).longValue();
      specs = specs.and(transferAmountGreaterThanEqual(longAmtMax));
    }

    if (searchTransferReq.getTransferDate() != null) {
      Instant transferDateFrom = searchTransferReq.getTransferDate()
          .atStartOfDay(ZONE_ID)
          .toInstant();
      Instant transferDateTo = searchTransferReq.getTransferDate()
          .atTime(LocalTime.MAX)
          .atZone(ZONE_ID)
          .toInstant();
      specs = specs.and(transferDateBetween(transferDateFrom, transferDateTo));
    }

    return specs;
  }

  private int getCurrencyScale(String currency) {
    return Currency.valueOf(currency).getScale();
  }
}

