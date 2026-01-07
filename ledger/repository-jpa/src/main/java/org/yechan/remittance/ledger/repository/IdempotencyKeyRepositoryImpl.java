package org.yechan.remittance.ledger.repository;

import org.yechan.remittance.ledger.IdempotencyKeyModel;
import org.yechan.remittance.ledger.IdempotencyKeyProps;
import org.yechan.remittance.ledger.IdempotencyKeyRepository;

public class IdempotencyKeyRepositoryImpl implements IdempotencyKeyRepository {

  private final IdempotencyKeyJpaRepository repository;

  public IdempotencyKeyRepositoryImpl(IdempotencyKeyJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public IdempotencyKeyModel save(IdempotencyKeyProps props) {
    return repository.save(IdempotencyKeyEntity.create(props));
  }
}
