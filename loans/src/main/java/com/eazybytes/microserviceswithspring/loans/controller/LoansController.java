package com.eazybytes.microserviceswithspring.loans.controller;

import com.eazybytes.microserviceswithspring.loans.config.LoansServiceConfig;
import com.eazybytes.microserviceswithspring.loans.model.Config;
import com.eazybytes.microserviceswithspring.loans.model.Customer;
import com.eazybytes.microserviceswithspring.loans.model.Loans;
import com.eazybytes.microserviceswithspring.loans.repository.LoansRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    private final LoansRepository loansRepository;

    private final LoansServiceConfig loansServiceConfig;

    @Autowired
    public LoansController(LoansRepository loansRepository, LoansServiceConfig loansServiceConfig) {
        this.loansRepository = loansRepository;
        this.loansServiceConfig = loansServiceConfig;
    }

    @PostMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestBody Customer customer) {
        List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
        return loans;
    }

    @GetMapping("/loans/config")
    public String getConfigDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Config config = new Config(
                loansServiceConfig.getMsg(),
                loansServiceConfig.getBuildVersion(),
                loansServiceConfig.getMailDetails(),
                loansServiceConfig.getActiveBranches()
        );
        String jsonStr = ow.writeValueAsString(config);
        return jsonStr;
    }


}
