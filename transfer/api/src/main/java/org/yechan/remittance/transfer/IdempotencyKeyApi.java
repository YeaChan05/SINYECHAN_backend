package org.yechan.remittance.transfer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.yechan.remittance.transfer.IdempotencyKeyProps.IdempotencyScopeValue;
import org.yechan.remittance.transfer.dto.IdempotencyKeyCreateResponse;

@Tag(name = "IdempotencyKey", description = "멱등키 API")
interface IdempotencyKeyApi {

  @Operation(summary = "멱등키 생성", description = "요청 스코프별 멱등키를 생성합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "생성 성공", content = @Content)
  })
  ResponseEntity<IdempotencyKeyCreateResponse> create(
      @Parameter(hidden = true) Long memberId,
      @Parameter(description = "스코프 (TRANSFER/DEPOSIT/WITHDRAW)")
      IdempotencyScopeValue scope
  );
}
