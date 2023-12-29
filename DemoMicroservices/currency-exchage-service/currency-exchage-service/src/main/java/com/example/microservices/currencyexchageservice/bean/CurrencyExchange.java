package com.example.microservices.currencyexchageservice.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class CurrencyExchange {

    @Id
    private Long id;
    @Column(name = "currency_from")
    private String from;
    @Column(name = "currency_to")
    private String to;

    //double ve float degerleri bellekte aynı şekilde saklanamazken
    // BigDecimal göründüğü gibi saklanır buda bize işlemlerimizde daha doğru sonuç verir.
    private BigDecimal currencyConversion;

    private String environment;
}
