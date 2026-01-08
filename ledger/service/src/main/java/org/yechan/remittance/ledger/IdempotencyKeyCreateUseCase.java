package org.yechan.remittance.ledger;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public interface IdempotencyKeyCreateUseCase {

  IdempotencyKeyModel create(IdempotencyKeyCreateProps props);
}

interface IdempotencyKeyCreateProps {

  long memberId();
}

class IdempotencyKeyService implements IdempotencyKeyCreateUseCase {

  private final IdempotencyKeyRepository repository;
  private final Clock clock;
  private final Duration expiresIn;

  IdempotencyKeyService(
      IdempotencyKeyRepository repository,
      Clock clock,
      IdempotencyKeyProperties properties
  ) {
    this(repository, clock, properties.expiresIn());
  }

  IdempotencyKeyService(IdempotencyKeyRepository repository, Clock clock, Duration expiresIn) {
    this.repository = repository;
    this.clock = clock;
    this.expiresIn = expiresIn;
  }

  @Override
  public IdempotencyKeyModel create(IdempotencyKeyCreateProps props) {
    var now = Instant.now(clock);
    var key = UUID.randomUUID().toString();
    return repository.save(new GeneratedIdempotencyKeyProps(props, key, now));
  }

  private class GeneratedIdempotencyKeyProps implements IdempotencyKeyProps {

    private final IdempotencyKeyCreateProps props;
    private final String key;
    private final Instant now;

    public GeneratedIdempotencyKeyProps(IdempotencyKeyCreateProps props, String key, Instant now) {
      this.props = props;
      this.key = key;
      this.now = now;
    }

    @Override
    public Long memberId() {
      return props.memberId();
    }

    @Override
    public String idempotencyKey() {
      return key;
    }

    @Override
    public Instant expiresAt() {
      return now.plus(expiresIn);
    }

    @Override
    public IdempotencyScopeValue scope() {
      return IdempotencyScopeValue.TRANSFER;
    }

    @Override
    public IdempotencyKeyStatusValue status() {
      return IdempotencyKeyStatusValue.BEFORE_START;
    }

    @Override
    public String requestHash() {
      return null;
    }

    @Override
    public String responseSnapshot() {
      return null;
    }

    @Override
    public Instant startedAt() {
      return null;
    }

    @Override
    public Instant completedAt() {
      return null;
    }
  }
}
