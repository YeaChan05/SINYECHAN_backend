package org.yechan.remittance.account;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
class AccountAutoConfiguration {

  @Bean
  AccountCreateUseCase accountCreateUseCase(AccountRepository accountRepository) {
    return new AccountService(accountRepository);
  }

  @Bean
  AccountDeleteUseCase accountDeleteUseCase(AccountRepository accountRepository) {
    return new AccountDeleteService(accountRepository);
  }
}
