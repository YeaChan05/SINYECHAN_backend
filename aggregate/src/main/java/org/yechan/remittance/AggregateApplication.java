package org.yechan.remittance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.yechan.remittance.config.AggregateSecurityConfiguration;

@SpringBootApplication
@Import(AggregateSecurityConfiguration.class)
public class AggregateApplication {

  public static void main(String[] args) {
    SpringApplication.run(AggregateApplication.class, args);
  }
}
