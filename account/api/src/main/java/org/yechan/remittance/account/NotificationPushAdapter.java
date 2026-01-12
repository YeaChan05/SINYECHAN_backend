package org.yechan.remittance.account;

class NotificationPushAdapter implements NotificationPushPort {

  private final NotificationSessionRegistry registry;

  NotificationPushAdapter(NotificationSessionRegistry registry) {
    this.registry = registry;
  }

  @Override
  public boolean push(Long memberId, TransferNotificationMessage message) {
    return registry.push(memberId, message);
  }
}
