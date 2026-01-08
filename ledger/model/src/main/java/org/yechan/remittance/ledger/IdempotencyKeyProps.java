package org.yechan.remittance.ledger;

import java.time.Instant;

public interface IdempotencyKeyProps {

  Long memberId();

  String idempotencyKey();

  Instant expiresAt();

  IdempotencyScopeValue scope();

  IdempotencyKeyStatusValue status();

  String requestHash();

  String responseSnapshot();

  Instant startedAt();

  Instant completedAt();

  enum IdempotencyKeyStatusValue {
    BEFORE_START,
    IN_PROGRESS,
    SUCCEEDED,
    FAILED,// TODO: 26. 1. 8. 오후 2:22 failure case 확장
    TIMEOUT
  }

  enum IdempotencyScopeValue {
    TRANSFER, // 계좌 이체
//    DEPOSIT,
//    WITHDRAW,
//    REFUND,
//    PAYMENT,
//    CANCEL_PAYMENT
  }
}
