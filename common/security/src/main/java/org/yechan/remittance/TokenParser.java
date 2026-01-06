package org.yechan.remittance;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface TokenParser {

  Optional<String> parse(HttpServletRequest request);
}
