package org.yechan.remittance;

import jakarta.persistence.EntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.transaction.support.TransactionTemplate;

@TestConfiguration
public class TransferTestFixturesConfig {

  @Bean
  TransferTestFixtures transferTestFixtures(
      RestTestClient restTestClient,
      EntityManager em,
      TransactionTemplate transactionTemplate,
      TokenVerifier tokenVerifier
  ) {
    return new TransferTestFixtures(restTestClient, em, transactionTemplate, tokenVerifier);
  }
}
