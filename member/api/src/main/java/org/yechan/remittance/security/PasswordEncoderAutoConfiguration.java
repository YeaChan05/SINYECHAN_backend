package org.yechan.remittance.security;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yechan.remittance.PasswordHashEncoder;

@AutoConfiguration
public class PasswordEncoderAutoConfiguration {
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  PasswordHashEncoder passwordHashEncoder(PasswordEncoder passwordEncoder) {
    return new BcryptPasswordHashEncoder(passwordEncoder);
  }
}
