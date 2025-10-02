package com.widetech.funds_transfer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "user_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

  @Id
  @ManyToOne
  private User user;

  @Id
  @ManyToOne
  private Role role;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserRole that = (UserRole) o;
    return Objects.equals(user, that.user) &&
        Objects.equals(role, that.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, role);
  }
}
