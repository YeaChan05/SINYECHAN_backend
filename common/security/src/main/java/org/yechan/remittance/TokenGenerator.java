package org.yechan.remittance;

public interface TokenGenerator {

  AuthTokenValue generate(Long memberId);
}
