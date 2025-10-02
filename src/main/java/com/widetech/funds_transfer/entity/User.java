package com.widetech.funds_transfer.entity;

import com.widetech.funds_transfer.enumeration.RoleName;
import com.widetech.funds_transfer.enumeration.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_id_seq")
  @SequenceGenerator(
      name = "app_user_id_seq",
      sequenceName = "app_user_id_seq",
      allocationSize = 1
  )
  private Long id;

  @Column(name = "username", length = 100, nullable = false, unique = true)
  private String username;

  @Column(name = "email", length = 100, nullable = false, unique = true)
  private String email;

  @Column(name = "password", length = 100, nullable = false)
  private String password;

  @Column(name = "user_status", length = 20, nullable = false)
  @Enumerated(EnumType.STRING)
  private UserStatus status;

  @Column(name = "family_name", length = 100)
  private String familyName;

  @Column(name = "given_name", length = 200)
  private String givenName;

  @OneToMany(mappedBy = Account_.USER)
  private List<Account> accounts = new ArrayList<>();

  @OneToMany(mappedBy = UserRole_.USER, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<UserRole> roles = new ArrayList<>();

  public void addAccount(Account account) {
    this.accounts.add(account);
    account.setUser(this);
  }

  public void removeAccount(Account account) {
    this.accounts.remove(account);
    account.setUser(null);
  }

  public void addRole(Role role) {
    UserRole userRole = new UserRole(this, role);
    this.roles.add(userRole);
  }

  public void removeRole(Role role) {
    UserRole userRole = new UserRole(this, role);
    this.roles.remove(userRole);
    userRole.setUser(null);
    userRole.setRole(null);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    List<Role> roleList = roles.stream()
        .map(UserRole::getRole)
        .toList();

    for (Role role : roleList) {
      authorities.add(new SimpleGrantedAuthority(role.getName().name()));
    }

    return authorities;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return status.isActive();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User that = (User) o;
    return Objects.equals(username, that.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username);
  }
}
