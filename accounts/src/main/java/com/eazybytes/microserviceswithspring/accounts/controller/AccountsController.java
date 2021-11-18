package com.eazybytes.microserviceswithspring.accounts.controller;

import com.eazybytes.microserviceswithspring.accounts.config.AccountsServiceConfig;
import com.eazybytes.microserviceswithspring.accounts.model.Accounts;
import com.eazybytes.microserviceswithspring.accounts.model.Config;
import com.eazybytes.microserviceswithspring.accounts.model.Customer;
import com.eazybytes.microserviceswithspring.accounts.repository.AccountsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    private final AccountsRepository accountsRepository;
    private final AccountsServiceConfig accountsServiceConfig;

    @Autowired
    public AccountsController(AccountsRepository accountsRepository, AccountsServiceConfig accountsServiceConfig) {
        this.accountsRepository = accountsRepository;
        this.accountsServiceConfig = accountsServiceConfig;
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

    @GetMapping("/accounts/config")
    public String getConfigDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Config config = new Config(
                accountsServiceConfig.getMsg(),
                accountsServiceConfig.getBuildVersion(),
                accountsServiceConfig.getMailDetails(),
                accountsServiceConfig.getActiveBranches()
        );
        String jsonStr = ow.writeValueAsString(config);
        return jsonStr;
    }
}
