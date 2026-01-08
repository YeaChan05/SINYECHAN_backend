package org.yechan.remittance.transfer;

import org.yechan.remittance.transfer.LedgerProps.LedgerSideValue;

public interface LedgerRepository {

  LedgerModel save(LedgerProps props);

  boolean existsByTransferIdAndAccountIdAndSide(Long transferId, Long accountId, LedgerSideValue side);
}
