package org.yechan.remittance.account;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({AccountController.class, NotificationApiController.class})
public class AccountApiAutoConfiguration {

  @Bean
  NotificationSessionRegistry notificationSessionRegistry() {
    return new NotificationSessionRegistry();
  }

  @Bean
  NotificationPushPort notificationPushPort(NotificationSessionRegistry registry) {
    return new NotificationPushAdapter(registry);
  }
}
