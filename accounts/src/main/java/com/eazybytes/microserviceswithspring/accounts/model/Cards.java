package com.eazybytes.microserviceswithspring.accounts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Cards {
    private long cardId;
    private int customerId;
    private String cardNumber;
    private String cardType;
    private int totalLimit;
    private String amountUsed;
    private String availableAmount;
    private LocalDate createDt;
}
