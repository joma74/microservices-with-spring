package com.eazybytes.microserviceswithspring.accounts.service.client;

import com.eazybytes.microserviceswithspring.accounts.model.Customer;
import com.eazybytes.microserviceswithspring.accounts.model.Loans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.core.MediaType;
import java.util.List;

@FeignClient("loans")
public interface LoansFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "myLoans", consumes = MediaType.APPLICATION_JSON)
    List<Loans> getLoansDetails(@RequestBody Customer customer);
}
