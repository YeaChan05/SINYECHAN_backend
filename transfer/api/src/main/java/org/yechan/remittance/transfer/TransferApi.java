package org.yechan.remittance.transfer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.yechan.remittance.transfer.dto.TransferRequest;

@Tag(name = "Transfer", description = "송금 API")
interface TransferApi {

  @Operation(summary = "송금 요청", description = "멱등키로 송금을 요청합니다.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "송금 성공", content = @Content)
  })
  TransferResult transfer(
      @Parameter(hidden = true) Long memberId,
      @Parameter(description = "멱등키") String idempotencyKey,
      TransferRequest props
  );
}
