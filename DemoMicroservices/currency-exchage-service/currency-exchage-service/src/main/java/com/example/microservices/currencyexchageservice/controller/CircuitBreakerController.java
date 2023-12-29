package com.example.microservices.currencyexchageservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
   // @Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
    @CircuitBreaker(name = "sample-api", fallbackMethod = "hardCodedResponse")
    //RateLimiter eklenebilir, hem annotation olarak hem app.prop a ;
    // belirli bir sürede en fazla ne kadar call yapabileceğini set ediyoruz.
    // Eğer o süre içerisinde verdiğimiz değeri geçerse hata atıyor.

    //BulkHead annotationı ve app.prop da ayarları kullanarakda
    // aynı anda kaç tane call yapabileceğini set ediyoruz.(concurrently)
    public String sampleApi() {
        logger.info("Sample api call received.");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return forEntity.getBody();
    }

    public String hardCodedResponse(Exception ex){
        return "hard coded response";
    }
}
