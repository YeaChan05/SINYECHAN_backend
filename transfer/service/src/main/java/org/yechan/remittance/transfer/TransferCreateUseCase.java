package org.yechan.remittance.transfer;

import static org.yechan.remittance.transfer.TransferSnapshotUtil.hashRequest;

import java.time.Clock;
import java.time.LocalDateTime;

public interface TransferCreateUseCase {

  TransferResult transfer(Long memberId, String idempotencyKey, TransferRequestProps props);
}

record TransferService(
    TransferIdempotencyHandler idempotencyHandler,
    TransferProcessService transferProcessService,
    LedgerWriter ledgerWriter,
    Clock clock
) implements TransferCreateUseCase {

  @Override
  public TransferResult transfer(Long memberId, String idempotencyKey, TransferRequestProps props) {
    LocalDateTime now = LocalDateTime.now(clock);
    var scope = toIdempotencyScope(props.scope());
    var key = idempotencyHandler.loadKey(memberId, idempotencyKey, scope, now);
    var requestHash = hashRequest(props);

    idempotencyHandler.validateRequestHash(key, requestHash);

    boolean marked = idempotencyHandler.markInProgress(
        memberId,
        idempotencyKey,
        scope,
        requestHash,
        now
    );

    if (!marked) {
      return idempotencyHandler.resolveExisting(memberId, idempotencyKey, scope, requestHash);
    }

    TransferResult result;
    try {
      result = transferProcessService.process(memberId, idempotencyKey, props, now);
    } catch (TransferFailedException ex) {
      TransferResult failed = TransferResult.failed(ex.getFailureCode());
      idempotencyHandler.markFailed(memberId, idempotencyKey, scope, failed, now);
      return failed;
    }

    try {
      ledgerWriter.record(props, result, now);
    } catch (RuntimeException ex) {
      throw new TransferLedgerRecordFailedException("Ledger record failed", ex);
    }

    return result;
  }

  private IdempotencyKeyProps.IdempotencyScopeValue toIdempotencyScope(
      TransferProps.TransferScopeValue scope
  ) {
    return switch (scope) {
      case TRANSFER -> IdempotencyKeyProps.IdempotencyScopeValue.TRANSFER;
      case WITHDRAW -> IdempotencyKeyProps.IdempotencyScopeValue.WITHDRAW;
      case DEPOSIT -> IdempotencyKeyProps.IdempotencyScopeValue.DEPOSIT;
      default -> IdempotencyKeyProps.IdempotencyScopeValue.TRANSFER;
    };
  }
}
