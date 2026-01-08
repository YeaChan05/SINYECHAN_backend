package org.yechan.remittance.transfer;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import(IdempotencyKeyController.class)
public class IdempotencyKeyApiAutoConfiguration {

}
