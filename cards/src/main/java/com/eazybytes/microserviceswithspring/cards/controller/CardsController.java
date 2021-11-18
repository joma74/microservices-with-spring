package com.eazybytes.microserviceswithspring.cards.controller;

import com.eazybytes.microserviceswithspring.cards.config.CardsServiceConfig;
import com.eazybytes.microserviceswithspring.cards.model.Cards;
import com.eazybytes.microserviceswithspring.cards.model.Config;
import com.eazybytes.microserviceswithspring.cards.model.Customer;
import com.eazybytes.microserviceswithspring.cards.repository.CardsRepository;
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
public class CardsController {

    private final CardsRepository cardsRepository;

    private final CardsServiceConfig cardsServiceConfig;

    @Autowired
    public CardsController(CardsRepository cardsRepository, CardsServiceConfig cardsServiceConfig) {
        this.cardsRepository = cardsRepository;
        this.cardsServiceConfig = cardsServiceConfig;
    }

    @PostMapping("/myCards")
    public List<Cards> getCardDetails(@RequestBody Customer customer) {
        List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
        return cards;
    }

    @GetMapping("/cards/config")
    public String getConfigDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Config config = new Config(
                cardsServiceConfig.getMsg(),
                cardsServiceConfig.getBuildVersion(),
                cardsServiceConfig.getMailDetails(),
                cardsServiceConfig.getActiveBranches()
        );
        String jsonStr = ow.writeValueAsString(config);
        return jsonStr;
    }
}
