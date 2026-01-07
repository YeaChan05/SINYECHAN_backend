package org.yechan.remittance.ledger;

import java.time.Instant;

public interface IdempotencyKeyProps {

  Long memberId();

  String idempotencyKey();

  Instant expiresAt();
}
