package org.yechan.remittance.member;

import org.yechan.remittance.PasswordHashEncoder;

public interface MemberCreateUseCase {

  MemberModel register(MemberProps props);
}


record MemberService(
    MemberRepository memberRepository,
    PasswordHashEncoder passwordHashEncoder
) implements MemberCreateUseCase {

  @Override
  public MemberModel register(MemberProps props) {
    // email duplication check
    memberRepository.findByEmail(props.email())
        .ifPresent(model -> {
          throw new MemberException("Email already exists: " + props.email());
        });
    return memberRepository.save(new EncodedMemberProps(props));
  }

  private class EncodedMemberProps implements MemberProps {

    private final MemberProps props;

    public EncodedMemberProps(MemberProps props) {
      this.props = props;
    }

    @Override
    public String name() {
      return props.name();
    }

    @Override
    public String email() {
      return props.email();
    }

    @Override
    public String password() {
      // password hash
      try {
        return passwordHashEncoder.encode(props.password());
      } catch (IllegalArgumentException e) {
        throw new MemberException("Invalid password: " + props.password() + ", " + e.getMessage());
      }
    }
  }
}
