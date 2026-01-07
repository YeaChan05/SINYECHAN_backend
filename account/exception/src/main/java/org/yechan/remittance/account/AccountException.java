package org.yechan.remittance.account;

import org.yechan.remittance.BusinessException;
import org.yechan.remittance.Status;

public class AccountException extends BusinessException {

  AccountException(String message) {
    super(message);
  }

  AccountException(Status status, String message) {
    super(status, message);
  }
}
