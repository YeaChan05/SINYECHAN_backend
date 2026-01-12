package org.yechan.remittance.member;

import org.yechan.remittance.PasswordHashEncoder;

public interface MemberAuthQueryUseCase {

  MemberAuthValue verify(MemberLoginProps props);
}


record MemberAuthQueryService(
    MemberRepository memberRepository,
    PasswordHashEncoder passwordHashEncoder
) implements MemberAuthQueryUseCase {

  @Override
  public MemberAuthValue verify(MemberLoginProps props) {
    var member = memberRepository.findByEmail(props.email());
    if (member.isEmpty()) {
      return new MemberAuthValue(false, 0L);
    }
    if (!passwordHashEncoder.matches(props.password(), member.get().password())) {
      return new MemberAuthValue(false, 0L);
    }
    return new MemberAuthValue(true, member.get().memberId());
  }
}
