package com.example.microservices.currencyconversionservice.controller;

import com.example.microservices.currencyconversionservice.bean.CurrencyConversion;
import com.example.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    //RestTemplate
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {
        CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);
        //currency-exchange servicinin response u ile currency-conversion classı dataları uyumlu,
        // o yuzden mapleniyor
        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,
                currencyConversion.getCurrencyConversion(),
                quantity.multiply(currencyConversion.getCurrencyConversion()),
                currencyConversion.getEnvironment());
    }


    //Feign
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        //RestTemplate can be used to make Rest Apı calls
        ResponseEntity<CurrencyConversion> responseEntity =
                new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyConversion = responseEntity.getBody();
        //currency-exchange servicinin response u ile currency-conversion classı dataları uyumlu,
        // o yuzden mapleniyor
        return new CurrencyConversion(currencyConversion.getId(), from, to, quantity,
                currencyConversion.getCurrencyConversion(),
                quantity.multiply(currencyConversion.getCurrencyConversion()),
                currencyConversion.getEnvironment());
    }
}
