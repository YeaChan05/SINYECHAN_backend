package org.yechan.remittance.transfer;

import org.yechan.remittance.Status;

public class TransferFailedException extends TransferException {

  private final TransferFailureCode failureCode;

  public TransferFailedException(TransferFailureCode failureCode, String message) {
    super(Status.BAD_REQUEST, message);
    this.failureCode = failureCode;
  }

  public TransferFailureCode getFailureCode() {
    return failureCode;
  }
}
