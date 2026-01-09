package org.yechan.remittance.transfer;

import org.yechan.remittance.Status;

public class TransferIdempotencyKeyExpiredException extends TransferException {

  public TransferIdempotencyKeyExpiredException(String message) {
    super(Status.BAD_REQUEST, message);
  }
}
