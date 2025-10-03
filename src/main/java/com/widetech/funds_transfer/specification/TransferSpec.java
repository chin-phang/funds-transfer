package com.widetech.funds_transfer.specification;

import com.widetech.funds_transfer.entity.Transfer;
import com.widetech.funds_transfer.entity.Transfer_;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public class TransferSpec {

  public static Specification<Transfer> refNoEqual(String refNo) {
    return (root, query, cb) -> cb.equal(root.get(Transfer_.refNo), refNo);
  }

  public static Specification<Transfer> fromAccountEqual(String accountNumber) {
    return (root, query, cb) -> cb.equal(root.get(Transfer_.fromAccount), accountNumber);
  }

  public static Specification<Transfer> toAccountEqual(String accountNumber) {
    return (root, query, cb) -> cb.equal(root.get(Transfer_.toAccount), accountNumber);
  }

  public static Specification<Transfer> transferStatusEqual(String transferStatus) {
    return (root, query, cb) -> cb.equal(root.get(Transfer_.status.getName()), transferStatus);
  }

  public static Specification<Transfer> transferTypeEqual(String transferType) {
    return (root, query, cb) -> cb.equal(root.get(Transfer_.type.getName()), transferType);
  }

  public static Specification<Transfer> currencyEqual(String currency) {
    return (root, query, cb) -> cb.equal(root.get(Transfer_.currency.getName()), currency);
  }

  public static Specification<Transfer> transferAmountGreaterThanEqual(Long amountMin) {
    return (root, query, cb) -> cb.equal(root.get(Transfer_.amount), amountMin);
  }

  public static Specification<Transfer> transferAmountLessThanEqual(Long amountMax) {
    return (root, query, cb) -> cb.equal(root.get(Transfer_.amount), amountMax);
  }

  public static Specification<Transfer> transferDateBetween(Instant transferDateFrom, Instant transferDateTo) {
    return (root, query, cb) ->
        cb.between(root.get(Transfer_.date), transferDateFrom, transferDateTo);
  }
}
