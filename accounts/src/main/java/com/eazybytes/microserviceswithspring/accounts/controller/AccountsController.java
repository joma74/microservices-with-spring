package com.eazybytes.microserviceswithspring.accounts.controller;

import com.eazybytes.microserviceswithspring.accounts.model.Accounts;
import com.eazybytes.microserviceswithspring.accounts.model.Customer;
import com.eazybytes.microserviceswithspring.accounts.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountsController(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @PostMapping("/myAccount")
    public Accounts getAccountDetails(@RequestBody Customer customer) {
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        if (accounts != null) {
            return accounts;
        } else {
            return null;
        }
    }
}
