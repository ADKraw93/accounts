package com.app.accounts.services;

import com.app.accounts.domain.Account;
import com.app.accounts.domain.AccountsList;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
@Service
public class AccountsListGenerator {

    public AccountsList generateAccounts() {

        Account account1 = new Account(1421, "72249000059957936727967706", "PLN", new BigDecimal("6525.11"));
        Account account2 = new Account(634, "72249000044332211727967707", "EUR", new BigDecimal("432.12"));
        Account account3 = new Account(521, "7224900004433221172796777", "USD", new BigDecimal("1888.93"));

        AccountsList accountsList = new AccountsList();
        accountsList.setAccounts(List.of(account1, account2, account3));

        return accountsList;
    }

    public AccountsList filterAccounts(long id) {

        AccountsList accountsList = generateAccounts();
        List<Account> filteredList = accountsList.getAccounts()
                .stream()
                .filter(t -> t.getId()==id)
                .collect(Collectors.toList());
        AccountsList result = new AccountsList(filteredList);

        return result;
    }
}
