package org.yechan.remittance;

import java.time.Clock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.yechan.remittance.config.AggregateSecurityConfiguration;

@SpringBootApplication
@Import(AggregateSecurityConfiguration.class)
public class AggregateApplication {
  @Bean
  Clock clock() {
    return Clock.systemUTC();
  }

  public static void main(String[] args) {
    SpringApplication.run(AggregateApplication.class, args);
  }
}
