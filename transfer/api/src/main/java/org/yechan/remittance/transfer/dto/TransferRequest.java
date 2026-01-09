package org.yechan.remittance.transfer.dto;

import static org.yechan.remittance.transfer.TransferFailureCode.INVALID_REQUEST;

import java.math.BigDecimal;
import org.yechan.remittance.transfer.TransferFailedException;
import org.yechan.remittance.transfer.TransferRequestProps;

public record TransferRequest(
    Long fromAccountId,
    Long toAccountId,
    BigDecimal amount
) implements TransferRequestProps {

  public TransferRequest {
    if (amount == null
        || amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new TransferFailedException(INVALID_REQUEST, "Invalid amount");
    }
    if (fromAccountId == null || toAccountId == null) {
      throw new TransferFailedException(INVALID_REQUEST, "Account IDs must not be null");
    }
  }
}
