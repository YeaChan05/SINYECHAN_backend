package org.yechan.remittance.account;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableRabbit
@ConditionalOnProperty(prefix = "account.transfer-notification", name = "enabled",
    havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(TransferNotificationConsumerProperties.class)
class TransferNotificationConsumerAutoConfiguration {

  @Bean
  TransferNotificationPayloadParser transferNotificationPayloadParser() {
    return new TransferNotificationPayloadParser();
  }

  @Bean
  DirectExchange transferNotificationExchange(TransferNotificationConsumerProperties properties) {
    return new DirectExchange(properties.getExchange());
  }

  @Bean
  Queue transferNotificationQueue(TransferNotificationConsumerProperties properties) {
    return new Queue(properties.getQueue());
  }

  @Bean
  Binding transferNotificationBinding(
      Queue transferNotificationQueue,
      DirectExchange transferNotificationExchange,
      TransferNotificationConsumerProperties properties
  ) {
    return BindingBuilder.bind(transferNotificationQueue)
        .to(transferNotificationExchange)
        .with(properties.getRoutingKey());
  }

  @Bean
  @ConditionalOnBean(TransferNotificationUseCase.class)
  TransferNotificationConsumer transferNotificationConsumer(
      TransferNotificationUseCase transferNotificationUseCase,
      TransferNotificationPayloadParser parser
  ) {
    return new TransferNotificationConsumer(transferNotificationUseCase, parser);
  }
}
