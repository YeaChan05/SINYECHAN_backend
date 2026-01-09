package org.yechan.remittance.transfer;

import org.yechan.remittance.Status;

public class TransferLedgerRecordFailedException extends TransferException {

  public TransferLedgerRecordFailedException(String message, Throwable cause) {
    super(Status.INTERNAL_SERVER_ERROR, message);
    initCause(cause);
  }
}
