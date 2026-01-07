package org.yechan.remittance.account;

import org.yechan.remittance.Status;

public class AccountPermissionDeniedException extends AccountException {

  public AccountPermissionDeniedException(String message) {
    super(Status.AUTHENTICATION_FAILED, message);
  }
}
