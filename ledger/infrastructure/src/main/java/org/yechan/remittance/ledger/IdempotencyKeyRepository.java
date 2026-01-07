package org.yechan.remittance.ledger;

public interface IdempotencyKeyRepository {

  IdempotencyKeyModel save(IdempotencyKeyProps props);
}
