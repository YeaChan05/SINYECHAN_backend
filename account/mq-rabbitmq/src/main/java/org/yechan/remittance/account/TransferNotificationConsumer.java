package org.yechan.remittance.account;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;

record TransferNotificationConsumer(
    TransferNotificationUseCase transferNotificationUseCase,
    TransferNotificationPayloadParser parser
) {

  private static final String EVENT_TYPE = "TRANSFER_COMPLETED";

  @RabbitListener(queues = "${account.transfer-notification.queue:transfer.completed.queue}")
  public void consume(
      String payload,
      @Header(value = "eventId", required = false) Long eventId,
      @Header(value = "eventType", required = false) String eventType
  ) {
    if (eventId == null || !EVENT_TYPE.equals(eventType)) {
      return;
    }
    TransferNotificationProps props = parser.parse(eventId, payload);
    transferNotificationUseCase.notify(props);
  }
}
