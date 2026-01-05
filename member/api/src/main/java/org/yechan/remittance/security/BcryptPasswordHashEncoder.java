package org.yechan.remittance.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.yechan.remittance.PasswordHashEncoder;

public class BcryptPasswordHashEncoder implements PasswordHashEncoder {

  private final PasswordEncoder passwordEncoder;

  public BcryptPasswordHashEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public String encode(String password) {
    return passwordEncoder.encode(password);
  }
}
