package org.yechan.remittance.api.login;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.yechan.remittance.EmailGenerator;
import org.yechan.remittance.PasswordGenerator;
import org.yechan.remittance.dto.MemberLoginRequest;
import org.yechan.remittance.dto.MemberLoginResponse;
import org.yechan.remittance.dto.MemberRegisterRequest;

@SpringBootTest
public class PostSpecs {

  @Autowired
  RestTestClient restTestClient;

  @Test
  void loginMember() {
    // Arrange
    var email = EmailGenerator.generate();
    var password = PasswordGenerator.generate();

    restTestClient.post()
        .uri("/members")
        .body(new MemberRegisterRequest("test", email, password))
        .exchange()
        .expectStatus().isOk();

    var request = new MemberLoginRequest(email, password);

    // Act
    var response = restTestClient.post()
        .uri("/login")
        .body(request)
        .exchange()
        .expectStatus().isOk()
        .expectBody(MemberLoginResponse.class)
        .returnResult()
        .getResponseBody();

    // Assert
    assertThat(response).isNotNull();
    assertThat(response.accessToken()).isNotBlank();
    assertThat(response.refreshToken()).isNotBlank();
    assertThat(response.expiresIn()).isPositive();
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "test@", "test@test"})
  void loginWithInvalidEmail(
      String email
  ) {
    // Arrange
    var request = new MemberLoginRequest(email, PasswordGenerator.generate());

    // Act & Assert
    restTestClient.post()
        .uri("/login")
        .body(request)
        .exchange()
        .expectStatus().isBadRequest();
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "pswd1!", "password", "password1", "password!", "12345678!"})
  void loginWithInvalidPassword(
      String password
  ) {
    // Arrange
    var request = new MemberLoginRequest(EmailGenerator.generate(), password);

    // Act & Assert
    restTestClient.post()
        .uri("/login")
        .body(request)
        .exchange()
        .expectStatus().isBadRequest();
  }

  @Test
  void loginWithInvalidCredentials() {
    // Arrange
    var email = EmailGenerator.generate();
    var password = PasswordGenerator.generate();

    restTestClient.post()
        .uri("/members")
        .body(new MemberRegisterRequest("test", email, password))
        .exchange()
        .expectStatus().isOk();

    // Act & Assert
    restTestClient.post()
        .uri("/login")
        .body(new MemberLoginRequest(email, PasswordGenerator.generate()))
        .exchange()
        .expectStatus().is5xxServerError()
        .expectBody(String.class)
        .consumeWith(res ->
            assertThat(Objects.requireNonNull(res.getResponseBody()))
                .contains("Invalid credentials"));
  }
}
