package org.yechan.remittance.transfer.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yechan.remittance.transfer.LedgerProps.LedgerSideValue;

interface LedgerJpaRepository extends JpaRepository<LedgerEntity, Long> {

  boolean existsByTransferIdAndAccountIdAndSide(Long transferId, Long accountId,
      LedgerSideValue side);

  @Query("""
      select coalesce(sum(l.amount), 0)
      from LedgerEntity l
      where l.accountId = :accountId
        and l.side = :side
        and l.createdAt >= :from
        and l.createdAt < :to
      """)
  BigDecimal sumAmountByAccountIdAndSideBetween(
      @Param("accountId") Long accountId,
      @Param("side") LedgerSideValue side,
      @Param("from") LocalDateTime from,
      @Param("to") LocalDateTime to
  );
}
