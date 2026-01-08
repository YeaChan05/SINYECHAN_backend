package org.yechan.remittance.transfer.dto;

import java.time.Instant;

public record IdempotencyKeyCreateResponse(
    String idempotencyKey,
    Instant expiresAt
) {

}
