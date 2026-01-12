package org.yechan.remittance.transfer;

import java.util.List;
import org.yechan.remittance.account.AccountRepository;

public interface TransferQueryUseCase {

  List<? extends TransferModel> query(
      Long memberId,
      Long accountId,
      TransferQueryCondition condition
  );
}

record TransferQueryService(
    AccountRepository accountRepository,
    TransferRepository transferRepository
) implements TransferQueryUseCase {

  @Override
  public List<? extends TransferModel> query(
      Long memberId,
      Long accountId,
      TransferQueryCondition condition
  ) {
    var account = accountRepository.findById(() -> accountId)
        .orElseThrow(() -> new TransferFailedException(
            TransferFailureCode.ACCOUNT_NOT_FOUND,
            "Account not found"
        ));

    if (!memberId.equals(account.memberId())) {
      throw new TransferFailedException(TransferFailureCode.INVALID_REQUEST,
          "Account owner mismatch");
    }

    return transferRepository.findCompletedByAccountId(() -> accountId, condition);
  }
}
