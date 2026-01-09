package org.yechan.remittance.transfer;

import org.yechan.remittance.BusinessException;
import org.yechan.remittance.Status;

public class TransferException extends BusinessException {

  TransferException(String message) {
    super(message);
  }

  TransferException(Status status, String message) {
    super(status, message);
  }
}
