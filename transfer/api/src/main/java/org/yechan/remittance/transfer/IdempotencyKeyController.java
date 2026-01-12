package org.yechan.remittance.transfer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yechan.remittance.LoginUserId;
import org.yechan.remittance.transfer.IdempotencyKeyProps.IdempotencyScopeValue;
import org.yechan.remittance.transfer.dto.IdempotencyKeyCreateResponse;

@RestController
@RequestMapping("/idempotency-keys")
record IdempotencyKeyController(
    IdempotencyKeyCreateUseCase idempotencyKeyCreateUseCase
) implements IdempotencyKeyApi {

  @Override
  @PostMapping
  public ResponseEntity<IdempotencyKeyCreateResponse> create(
      @LoginUserId Long memberId,
      @RequestParam(required = false) IdempotencyScopeValue scope
  ) {
    IdempotencyScopeValue resolvedScope = scope == null
        ? IdempotencyScopeValue.TRANSFER
        : scope;
    var created = idempotencyKeyCreateUseCase.create(
        new IdempotencyKeyCreateCommand(memberId, resolvedScope));
    return ResponseEntity.ok(
        new IdempotencyKeyCreateResponse(created.idempotencyKey(), created.expiresAt()));
  }

  private record IdempotencyKeyCreateCommand(
      long memberId,
      IdempotencyScopeValue scope
  ) implements IdempotencyKeyCreateProps {

  }
}
