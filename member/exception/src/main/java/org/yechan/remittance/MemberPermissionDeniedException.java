package org.yechan.remittance;

public class MemberPermissionDeniedException extends BusinessException {

  public MemberPermissionDeniedException(String message) {
    super(message);
  }

  public MemberPermissionDeniedException(Status status, String message) {
    super(status, message);
  }
}
