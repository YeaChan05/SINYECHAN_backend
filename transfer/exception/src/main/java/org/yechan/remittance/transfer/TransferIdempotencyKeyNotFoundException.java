package org.yechan.remittance.transfer;

import org.yechan.remittance.Status;

public class TransferIdempotencyKeyNotFoundException extends TransferException {

  public TransferIdempotencyKeyNotFoundException(String message) {
    super(Status.RESOURCE_NOT_FOUND, message);
  }
}
