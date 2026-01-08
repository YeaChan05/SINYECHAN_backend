package org.yechan.remittance.transfer;

import java.time.LocalDateTime;

public record Ledger(
    Long ledgerId,
    Long transferId,
    Long accountId,
    Long amount,
    LedgerSideValue side,
    LocalDateTime createdAt
) implements LedgerModel {

}
