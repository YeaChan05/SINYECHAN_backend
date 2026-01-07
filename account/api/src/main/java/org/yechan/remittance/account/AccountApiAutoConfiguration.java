package org.yechan.remittance.account;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import(AccountController.class)
public class AccountApiAutoConfiguration {

}
