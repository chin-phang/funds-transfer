package com.widetech.funds_transfer.entity;

import com.widetech.funds_transfer.enumeration.RoleName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_role_id_seq")
  @SequenceGenerator(
      name = "app_role_id_seq",
      sequenceName = "app_role_id_seq",
      allocationSize = 1
  )
  private Long id;

  @Column(name = "name", length = 100, nullable = false, unique = true)
  @Enumerated(EnumType.STRING)
  private RoleName name;
}
