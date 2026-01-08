package org.yechan.remittance.transfer;

import java.time.Duration;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "transfer.idempotency-key")
@Validated
public record IdempotencyKeyProperties(
    @NonNull Duration expiresIn
) {

}
