package org.yechan.remittance.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.yechan.remittance.account.dto.AccountCreateRequest;
import org.yechan.remittance.account.dto.AccountCreateResponse;
import org.yechan.remittance.account.dto.AccountDeleteResponse;

@Tag(name = "Account", description = "계좌 API")
interface AccountApi {

  @Operation(summary = "계좌 등록", description = "회원의 계좌를 등록합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "등록 성공", content = @Content)
  })
  ResponseEntity<AccountCreateResponse> create(
      @Parameter(hidden = true) Long memberId,
      AccountCreateRequest request
  );

  @Operation(summary = "계좌 삭제", description = "회원의 계좌를 삭제합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content)
  })
  ResponseEntity<AccountDeleteResponse> delete(
      @Parameter(hidden = true) Long memberId,
      @Parameter(description = "계좌 ID") Long accountId
  );
}
