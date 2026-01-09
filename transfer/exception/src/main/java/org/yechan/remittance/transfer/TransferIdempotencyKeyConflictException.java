package org.yechan.remittance.transfer;

import org.yechan.remittance.Status;

public class TransferIdempotencyKeyConflictException extends TransferException {

  public TransferIdempotencyKeyConflictException(String message) {
    super(Status.BAD_REQUEST, message);
  }
}
