package com.app.accounts.controller;

import com.app.accounts.domain.AccountsList;
import com.app.accounts.domain.AccountsListDto;
import com.app.accounts.services.AccountsListGenerator;
import com.app.accounts.services.AccountsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AccountsController {
    private final AccountsListGenerator generator;
    private final AccountsMapper mapper;

    @GetMapping
    public ResponseEntity<AccountsListDto> getAccounts() {
        AccountsList accounts = generator.generateAccounts();
        return ResponseEntity.ok(mapper.mapToAccountsListDto(accounts));
    }

    @GetMapping(value = "{customerId}")
    public ResponseEntity<AccountsListDto> getTask(@PathVariable Long id) throws CustomerNotFoundException {
        return ResponseEntity.ok(mapper.mapToAccountsListDto(generator.filterAccounts(id)));
    }
}
