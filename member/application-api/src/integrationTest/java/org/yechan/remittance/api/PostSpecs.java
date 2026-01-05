package org.yechan.remittance.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.yechan.remittance.dto.MemberRegisterRequest;
import org.yechan.remittance.dto.MemberRegisterResponse;

@SpringBootTest
public class PostSpecs {
  @Autowired
  RestTestClient restTestClient;

  @Test
  void registerMember() {
    // Arrange
    var name = "test";
    var request = new MemberRegisterRequest(name);

    // Act
    var response = restTestClient.post()
        .uri("/members")
        .body(request)
        .exchange()
        .expectStatus().isOk()
        .expectBody(MemberRegisterResponse.class)
        .returnResult()
        .getResponseBody();

    // Assert
    assertThat(response).isNotNull();
    assertThat(response.name()).isEqualTo(name);
  }

}
