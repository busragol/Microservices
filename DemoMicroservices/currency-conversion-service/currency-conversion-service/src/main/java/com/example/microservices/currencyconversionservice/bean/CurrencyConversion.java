package com.example.microservices.currencyconversionservice.bean;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyConversion {

    private Long id;
    private String from;
    private String to;
    private BigDecimal currencyConversion;
    private BigDecimal quantity;
    private BigDecimal totalCalculatedAmount;
    private String environment;

}
