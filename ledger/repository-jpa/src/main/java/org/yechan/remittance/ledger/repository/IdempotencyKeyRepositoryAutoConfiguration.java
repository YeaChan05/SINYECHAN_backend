package org.yechan.remittance.ledger.repository;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.data.jpa.autoconfigure.DataJpaRepositoriesAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.yechan.remittance.ledger.IdempotencyKeyRepository;

@AutoConfiguration(before = DataJpaRepositoriesAutoConfiguration.class)
@EntityScan(basePackageClasses = {
    IdempotencyKeyEntity.class,
    TransferEntity.class,
    OutboxEventEntity.class
})
@EnableJpaRepositories(basePackageClasses = IdempotencyKeyJpaRepository.class)
public class IdempotencyKeyRepositoryAutoConfiguration {

  @Bean
  IdempotencyKeyRepository idempotencyKeyRepository(IdempotencyKeyJpaRepository repository) {
    return new IdempotencyKeyRepositoryImpl(repository);
  }
}
