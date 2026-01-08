package org.yechan.remittance.transfer;

public interface OutboxEventProps {

  String aggregateType();

  String aggregateId();

  String eventType();

  String payload();

  OutboxEventStatusValue status();

  enum OutboxEventStatusValue {
    NEW,
    SENT
  }
}
