package org.yechan.remittance.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.yechan.remittance.member.dto.MemberRegisterRequest;
import org.yechan.remittance.member.dto.MemberRegisterResponse;

@Tag(name = "Member", description = "회원 API")
interface MemberApi {

  @Operation(summary = "회원 가입", description = "신규 회원을 등록합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "등록 성공", content = @Content)
  })
  ResponseEntity<MemberRegisterResponse> register(MemberRegisterRequest request);
}
