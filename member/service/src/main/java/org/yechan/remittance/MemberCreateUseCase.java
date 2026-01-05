package org.yechan.remittance;

public interface MemberCreateUseCase {

  MemberModel register(MemberProps props);
}


class MemberService implements MemberCreateUseCase {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public MemberModel register(MemberProps props) {
    return memberRepository.save(props);
  }
}
