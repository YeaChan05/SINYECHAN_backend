package org.yechan.remittance.account;

import org.yechan.remittance.Status;

public class AccountNotFoundException extends AccountException {

  public AccountNotFoundException(String message) {
    super(Status.RESOURCE_NOT_FOUND, message);
  }
}
