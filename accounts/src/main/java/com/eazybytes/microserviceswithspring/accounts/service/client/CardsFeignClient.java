package com.eazybytes.microserviceswithspring.accounts.service.client;

import com.eazybytes.microserviceswithspring.accounts.model.Cards;
import com.eazybytes.microserviceswithspring.accounts.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.core.MediaType;
import java.util.List;

@FeignClient("cards")
public interface CardsFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "myCards", consumes = MediaType.APPLICATION_JSON)
    List<Cards> getCardDetails(@RequestBody Customer customer);
}
