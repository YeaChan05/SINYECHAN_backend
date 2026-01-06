package org.yechan.remittance;

import org.springframework.security.core.Authentication;

public interface TokenVerifier {

  Authentication verify(String token);
}
