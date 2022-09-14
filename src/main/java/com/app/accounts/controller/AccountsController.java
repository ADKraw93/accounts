package com.app.accounts.controller;

import com.app.accounts.domain.AccountsList;
import com.app.accounts.domain.AccountsListDto;
import com.app.accounts.services.AccountsListGenerator;
import com.app.accounts.services.AccountsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AccountsController {
    private final AccountsListGenerator generator;
    private final AccountsMapper mapper;

    @GetMapping("/allAccounts")
    public ResponseEntity<AccountsListDto> getAccounts() {
        AccountsList accounts = generator.generateAccounts();
        System.out.println("getAccounts method used");
        return ResponseEntity.ok(mapper.mapToAccountsListDto(accounts));
    }

    @GetMapping("/accounts")
    public ResponseEntity<AccountsListDto> getCustomerData(@RequestParam Long customerId) throws CustomerNotFoundException {
        System.out.println("getCustomerData method used");
        return ResponseEntity.ok(mapper.mapToAccountsListDto(generator.filterAccounts(customerId)));
    }
}
