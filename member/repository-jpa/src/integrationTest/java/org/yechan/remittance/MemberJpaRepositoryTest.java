package org.yechan.remittance;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.TestConstructor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class MemberJpaRepositoryTest {

  @Autowired
  MemberRepository memberRepository;
  @Autowired
  EntityManager entityManager;

  @Test
  void registerMember() {
    MemberEntity member = MemberEntity.create("test");
    MemberModel saved = memberRepository.save(member);
    entityManager.flush();

    assertThat(saved.memberId()).isNotNull();
    var byId = memberRepository.findById(saved);
    assertThat(byId).isPresent();
    assertThat(byId.get()).isEqualTo(saved);
  }
}
