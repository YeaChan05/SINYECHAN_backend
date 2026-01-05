package org.yechan.remittance;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(AuthTokenProperties.class)
class MemberAutoConfiguration {

  @Bean
  MemberCreateUseCase memberCreateUseCase(
      MemberRepository memberRepository,
      PasswordHashEncoder passwordHashEncoder) {
    return new MemberService(memberRepository, passwordHashEncoder);
  }

  @Bean
  MemberQueryUseCase memberQueryUseCase(
      MemberRepository memberRepository,
      PasswordHashEncoder passwordHashEncoder,
      TokenGenerator tokenGenerator) {
    return new MemberQueryService(memberRepository, passwordHashEncoder, tokenGenerator);
  }

  @Bean
  TokenGenerator tokenGenerator(AuthTokenProperties authTokenProperties) {
    return new JwtTokenGenerator(
        authTokenProperties.salt(),
        authTokenProperties.accessExpiresIn(),
        authTokenProperties.refreshExpiresIn());
  }
}
