package org.yechan.remittance.ledger;

import java.time.Instant;

public interface IdempotencyKeyModel extends IdempotencyKeyProps, IdempotencyKeyIdentifier {

  Long memberId();

  String idempotencyKey();

  Instant expiresAt();
}
