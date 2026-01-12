package org.yechan.remittance.account;

public interface AccountCreateUseCase {

  AccountModel create(AccountProps props);
}


record AccountService(
    AccountRepository accountRepository
) implements AccountCreateUseCase {

  @Override
  public AccountModel create(AccountProps props) {
    accountRepository.findByMemberIdAndBankCodeAndAccountNumber(
            props.memberId(),
            props.bankCode(),
            props.accountNumber())
        .ifPresent(account -> {
          throw new AccountDuplicateException("Account already exists");
        });
    return accountRepository.save(props);
  }
}
