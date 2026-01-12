package org.yechan.remittance.auth;

import org.yechan.remittance.AuthTokenValue;
import org.yechan.remittance.TokenGenerator;

public interface AuthLoginUseCase {

  AuthTokenValue login(AuthLoginProps props);
}


record AuthService(
    MemberAuthClient memberAuthClient,
    TokenGenerator tokenGenerator
) implements AuthLoginUseCase {


  @Override
  public AuthTokenValue login(AuthLoginProps props) {
    var result = memberAuthClient.verify(props.email(), props.password());
    if (!result.valid()) {
      throw new AuthInvalidCredentialException("Invalid credentials");
    }
    return tokenGenerator.generate(result.memberId());
  }
}
