package com.widetech.funds_transfer.service;

import com.widetech.funds_transfer.entityGraphPath.UserEntityGraphPath;
import com.widetech.funds_transfer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username, UserEntityGraphPath.UserWithRoles.getEntityGraph())
        .orElseThrow(() -> new UsernameNotFoundException(
            String.format("User: %s, not found", username)
        ));
  }
}
