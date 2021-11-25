package com.eazybytes.microserviceswithspring.accounts.controller;

import com.eazybytes.microserviceswithspring.accounts.config.AccountsServiceConfig;
import com.eazybytes.microserviceswithspring.accounts.model.*;
import com.eazybytes.microserviceswithspring.accounts.repository.AccountsRepository;
import com.eazybytes.microserviceswithspring.accounts.service.client.CardsFeignClient;
import com.eazybytes.microserviceswithspring.accounts.service.client.LoansFeignClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountsController {

    private final AccountsRepository accountsRepository;
    private final AccountsServiceConfig accountsServiceConfig;
    LoansFeignClient loansFeignClient;
    CardsFeignClient cardsFeignClient;

    @Autowired
    public AccountsController(AccountsRepository accountsRepository, AccountsServiceConfig accountsServiceConfig, LoansFeignClient loansFeignClient, CardsFeignClient cardsFeignClient) {
        this.accountsRepository = accountsRepository;
        this.accountsServiceConfig = accountsServiceConfig;
        this.loansFeignClient = loansFeignClient;
        this.cardsFeignClient = cardsFeignClient;
    }

    @PostMapping("/myAccount")
    public Accounts getAccountDetails(@RequestBody Customer customer) {
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        return accounts;
    }

    @GetMapping(value = "/accounts/config", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PostMapping("/myCustomerDetails")
    public CustomerDetails myCustomerDetails(@RequestBody Customer customer) {
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        List<Loans> loans = loansFeignClient.getLoansDetails(customer);
        List<Cards> cards = cardsFeignClient.getCardDetails(customer);

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(accounts);
        customerDetails.setLoans(loans);
        customerDetails.setCards(cards);

        return customerDetails;

    }
}
