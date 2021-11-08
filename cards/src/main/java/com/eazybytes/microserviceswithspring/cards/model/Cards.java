package com.eazybytes.microserviceswithspring.cards.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private long accountNumber;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "amount_used")
    private String amountUsed;

    @Column(name = "available_amount")
    private String availableAmount;

    @Column(name = "create_dt")
    private LocalDate createDt;
}
