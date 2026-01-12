package org.yechan.remittance.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yechan.remittance.transfer.LedgerProps.LedgerSideValue;

interface LedgerJpaRepository extends JpaRepository<LedgerEntity, Long> {

  boolean existsByTransferIdAndAccountIdAndSide(Long transferId, Long accountId,
      LedgerSideValue side);
}
