package com.example.microservices.limitsservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("limits-service")
@Data
@Component
public class Configuration {
   //limits-service.minimum asagidaki degere maplenecek
    private int minimum;
    private int maximum;
}
