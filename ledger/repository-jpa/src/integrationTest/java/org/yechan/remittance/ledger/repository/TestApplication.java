package org.yechan.remittance.ledger.repository;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import(
    IdempotencyKeyRepositoryAutoConfiguration.class
)
@EnableAutoConfiguration
@SpringBootConfiguration
public class TestApplication {

}
