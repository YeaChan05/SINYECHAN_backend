package org.yechan.remittance;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalExceptionHandler {
  @ExceptionHandler(BusinessException.class)
  ResponseEntity<?> handleBusinessException(BusinessException e) {
    return ResponseEntity.internalServerError().body(e);
  }
}
