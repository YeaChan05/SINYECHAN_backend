package org.yechan.remittance.ledger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class IdempotencyKeyControllerTest {

  @Test
  void createIdempotencyKeyReturnsResponse() {
    Instant expiresAt = Instant.parse("2024-01-01T01:00:00Z");
    IdempotencyKeyCreateUseCase useCase = props -> new TestIdempotencyKeyModel(expiresAt);

    var controller = new IdempotencyKeyController(useCase);

    var response = controller.create(1L);

    assertNotNull(response.getBody());
    assertEquals("key", response.getBody().idempotencyKey());
    assertEquals(expiresAt, response.getBody().expiresAt());
  }

  private record TestIdempotencyKeyModel(Instant expiresAt) implements IdempotencyKeyModel {

    @Override
      public Long idempotencyKeyId() {
        return 1L;
      }

      @Override
      public Long memberId() {
        return 1L;
      }

      @Override
      public String idempotencyKey() {
        return "key";
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
