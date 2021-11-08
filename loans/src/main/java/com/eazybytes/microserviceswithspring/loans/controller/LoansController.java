package com.eazybytes.microserviceswithspring.loans.controller;

import com.eazybytes.microserviceswithspring.loans.model.Customer;
import com.eazybytes.microserviceswithspring.loans.model.Loans;
import com.eazybytes.microserviceswithspring.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    private LoansRepository loansRepository;

    @Autowired
    public LoansController(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    public List<Loans> getLoansDetails(@RequestBody Customer customer) {
        List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
        if (loans != null) {
            return loans;
        } else {
            return null;
        }
    }


}
