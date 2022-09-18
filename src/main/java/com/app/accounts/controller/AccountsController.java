package com.app.accounts.controller;

import com.app.accounts.domain.AccountsList;
import com.app.accounts.domain.AccountsListDto;
import com.app.accounts.services.AccountsListGenerator;
import com.app.accounts.services.AccountsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AccountsController {
    private final AccountsListGenerator generator;
    private final AccountsMapper mapper;

    @Value("${application.allow-get-accounts}")
    private boolean allowGetAccounts;

    @GetMapping("/allAccounts")
    public ResponseEntity<AccountsListDto> getAccounts() {
        AccountsList accounts = generator.generateAccounts();
        System.out.println("getAccounts method used");
        return ResponseEntity.ok(mapper.mapToAccountsListDto(accounts));
    }

    @GetMapping("/accounts")
    public ResponseEntity<AccountsListDto> getCustomerData(@RequestParam Long customerId) throws CustomerNotFoundException {
        if(!allowGetAccounts) {
            log.info("Getting accounts is disabled");
            throw  new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Getting accounts is disabled");
        }
        System.out.println("getCustomerData method used");
        return ResponseEntity.ok(mapper.mapToAccountsListDto(generator.filterAccounts(customerId)));
    }
}
