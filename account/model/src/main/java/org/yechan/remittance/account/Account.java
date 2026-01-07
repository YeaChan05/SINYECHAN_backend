package org.yechan.remittance.account;

public record Account(
    Long accountId,
    Long memberId,
    String bankCode,
    String accountNumber,
    String accountName
) implements AccountModel {

}
