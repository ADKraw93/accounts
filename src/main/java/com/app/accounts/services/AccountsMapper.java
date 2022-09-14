package com.app.accounts.services;

import com.app.accounts.domain.Account;
import com.app.accounts.domain.AccountDto;
import com.app.accounts.domain.AccountsList;
import com.app.accounts.domain.AccountsListDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Service
public class AccountsMapper {

    public AccountDto mapToAccountDto(Account account) {
        AccountDto result = new AccountDto(account.getId(), account.getNrb(), account.getCurrency(), account.getAvailableFunds());
        return result;
    }

    public AccountsListDto mapToAccountsListDto(AccountsList accountsList) {
        List<AccountDto> accountsDtoList = accountsList.getAccounts()
                .stream()
                .map(this::mapToAccountDto)
                .collect(Collectors.toList());
        AccountsListDto result = new AccountsListDto(accountsDtoList);
        return result;
    }
}
