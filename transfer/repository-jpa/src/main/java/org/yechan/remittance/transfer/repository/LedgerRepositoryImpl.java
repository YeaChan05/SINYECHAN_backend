package org.yechan.remittance.transfer.repository;

import org.yechan.remittance.transfer.LedgerModel;
import org.yechan.remittance.transfer.LedgerProps;
import org.yechan.remittance.transfer.LedgerProps.LedgerSideValue;
import org.yechan.remittance.transfer.LedgerRepository;

public class LedgerRepositoryImpl implements LedgerRepository {

  private final LedgerJpaRepository repository;

  public LedgerRepositoryImpl(LedgerJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public LedgerModel save(LedgerProps props) {
    return repository.save(LedgerEntity.create(props));
  }

  @Override
  public boolean existsByTransferIdAndAccountIdAndSide(Long transferId, Long accountId,
      LedgerSideValue side) {
    return repository.existsByTransferIdAndAccountIdAndSide(transferId, accountId, side);
  }

}
