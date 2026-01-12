package org.yechan.remittance.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

class TransferNotificationPayloadParser {

  TransferNotificationProps parse(Long eventId, String payload) {
    Map<String, String> values = parsePayload(payload);
    return new TransferNotificationMessage(
        eventId,
        Long.parseLong(values.get("transferId")),
        Long.parseLong(values.get("toAccountId")),
        Long.parseLong(values.get("fromAccountId")),
        new BigDecimal(values.get("amount")),
        LocalDateTime.parse(values.get("completedAt"))
    );
  }

  private Map<String, String> parsePayload(String payload) {
    return Arrays.stream(payload.split("\\|"))
        .map(token -> token.split("=", 2))
        .filter(pair -> pair.length == 2)
        .collect(Collectors.toMap(pair -> pair[0], pair -> pair[1], (a, b) -> b));
  }

  private record TransferNotificationMessage(
      Long eventId,
      Long transferId,
      Long toAccountId,
      Long fromAccountId,
      BigDecimal amount,
      LocalDateTime occurredAt
  ) implements TransferNotificationProps {

  }
}
