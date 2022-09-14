package com.app.accounts.services;

import com.app.accounts.domain.Account;
import com.app.accounts.domain.AccountsList;

import java.math.BigDecimal;
import java.util.List;

public class AccountsListGenerator {

    public AccountsList generateAccounts() {

        Account account1 = new Account(1421, "72249000059957936727967706", "PLN", new BigDecimal("6525.11"));
        Account account2 = new Account(634, "72249000044332211727967707", "EUR", new BigDecimal("432.12"));

        AccountsList accountsList = new AccountsList();
        accountsList.setAccounts(List.of(account1, account2));

        return accountsList;
    }
}
