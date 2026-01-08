package org.yechan.remittance.transfer;

import java.time.Instant;

public record IdempotencyKey(
    Long idempotencyKeyId,
    Long memberId,
    String idempotencyKey,
    Instant expiresAt,
    IdempotencyScopeValue scope,
    IdempotencyKeyStatusValue status,
    String requestHash,
    String responseSnapshot,
    Instant startedAt,
    Instant completedAt
) implements IdempotencyKeyModel {

}
