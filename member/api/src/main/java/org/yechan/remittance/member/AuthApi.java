package org.yechan.remittance.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.yechan.remittance.member.dto.MemberLoginRequest;
import org.yechan.remittance.member.dto.MemberLoginResponse;

@Tag(name = "Auth", description = "인증 API")
interface AuthApi {

  @Operation(summary = "로그인", description = "회원 로그인을 처리합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content)
  })
  ResponseEntity<MemberLoginResponse> login(MemberLoginRequest request);
}
