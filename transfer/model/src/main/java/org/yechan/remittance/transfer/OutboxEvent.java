package org.yechan.remittance.transfer;

import java.time.Instant;

public record OutboxEvent(
    Long eventId,
    String aggregateType,
    String aggregateId,
    String eventType,
    String payload,
    OutboxEventStatusValue status,
    Instant createdAt
) implements OutboxEventModel {

}
