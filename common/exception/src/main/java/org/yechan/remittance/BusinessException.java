package org.yechan.remittance;

class BusinessException extends RuntimeException {
  private final Status status;

  BusinessException(Status status, String message) {
    super(message);
    this.status = status;
  }

  BusinessException(String message) {
    super(message);
    this.status = Status.INTERNAL_SERVER_ERROR;
  }
}
