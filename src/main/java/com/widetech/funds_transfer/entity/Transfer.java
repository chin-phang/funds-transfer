package com.widetech.funds_transfer.entity;

import com.widetech.funds_transfer.enumeration.Currency;
import com.widetech.funds_transfer.enumeration.TransferStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "transfer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transfer extends Auditable {

  @Id
  private String refNo;

  @Column(name = "transfer_date", nullable = false)
  private Instant date;

  @Column(name = "transfer_type", nullable = false)
  private String type;

  @Column(name = "from_account", length = 20, nullable = false)
  private String fromAccount;

  @Column(name = "to_account", length = 20, nullable = false)
  private String toAccount;

  @Column(name = "transfer_amount", nullable = false)
  private String amount;

  @Column(name = "transfer_status", length = 20, nullable = false)
  @Enumerated(EnumType.STRING)
  private TransferStatus status;

  @Column(name = "currency", length = 3, nullable = false)
  @Enumerated(EnumType.STRING)
  private Currency currency;
}
