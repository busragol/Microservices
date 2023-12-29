package com.example.microservices.currencyexchageservice.controller;

import com.example.microservices.currencyexchageservice.bean.CurrencyExchange;
import com.example.microservices.currencyexchageservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
       /* return CurrencyExchange.builder()
                .id(1000L)
                .from(from)
                .to(to)
                .currencyConversion(BigDecimal.valueOf(50))
                .environment(environment.getProperty("local.server.port"))
                .build();*/
        if(currencyExchange == null){
            throw new RuntimeException("There is no data");
        }
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}
