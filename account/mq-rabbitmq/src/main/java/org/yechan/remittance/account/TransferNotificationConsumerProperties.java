package org.yechan.remittance.account;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("account.transfer-notification")
class TransferNotificationConsumerProperties {

  private String queue = "transfer.completed.queue";
  private String exchange = "transfer.exchange";
  private String routingKey = "transfer.completed";

  public String getQueue() {
    return queue;
  }

  public void setQueue(String queue) {
    this.queue = queue;
  }

  public String getExchange() {
    return exchange;
  }

  public void setExchange(String exchange) {
    this.exchange = exchange;
  }

  public String getRoutingKey() {
    return routingKey;
  }

  public void setRoutingKey(String routingKey) {
    this.routingKey = routingKey;
  }
}
