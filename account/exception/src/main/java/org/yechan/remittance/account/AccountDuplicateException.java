package org.yechan.remittance.account;

import org.yechan.remittance.Status;

public class AccountDuplicateException extends AccountException {

  public AccountDuplicateException(String message) {
    super(Status.BAD_REQUEST, message);
  }
}
