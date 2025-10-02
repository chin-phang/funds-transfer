package com.widetech.funds_transfer.entity;

import com.widetech.funds_transfer.enumeration.AccountStatus;
import com.widetech.funds_transfer.enumeration.AccountType;
import com.widetech.funds_transfer.enumeration.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends Auditable {

  @Id
  private String accountNo;

  @Column(name = "account_type", length = 20, nullable = false)
  @Enumerated(EnumType.STRING)
  private AccountType type;

  @Column(name = "account_status", length = 20, nullable = false)
  @Enumerated(EnumType.STRING)
  private AccountStatus status;

  @Column(name = "currency", length = 3, nullable = false)
  @Enumerated(EnumType.STRING)
  private Currency currency;

  @Column(name = "account_balance", nullable = false)
  private Long balance;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;
}
