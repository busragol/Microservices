package com.example.microservices.apigateway.configuration;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        Function<PredicateSpec, Buildable<Route>> routeFunction
                = p -> p.path("/get")
                .filters(f -> f
                        .addRequestHeader("MyHeader", "MyURI")
                        .addRequestParameter("Param", "MyValue"))
                .uri("http://httpbin.org:80");

        //Bizi iki kez /currency-conversion/currency-conversion yazmaktan kurtardı
        //İki kez currency-exchange yazılmasının sebebi ilkinin eureka üzerinden alındıgından, eureka yı
        //kullanarak doğru servisi buluyoruz.
        Function<PredicateSpec, Buildable<Route>> routeFunction2
                =  p -> p.path("/currency-exchange/**")
                .uri("lb://currency-exchange"); //lb load balance demek

        Function<PredicateSpec, Buildable<Route>> routeFunction3
                =  p -> p.path("/currency-conversion/**")
                .uri("lb://currency-conversion"); //lb load balance demek

        Function<PredicateSpec, Buildable<Route>> routeFunction4
                =  p -> p.path("/currency-conversion-feign/**")
                .uri("lb://currency-conversion"); //lb load balance demek

        Function<PredicateSpec, Buildable<Route>> routeFunction5
                =  p -> p.path("/currency-conversion-new/**")
                .filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)",
                        "/currency-conversion-feign/${segment}"))
                .uri("lb://currency-conversion");


        return builder.routes()
                .route(routeFunction)
                .route(routeFunction2)
                .route(routeFunction3)
                .route(routeFunction4)
                .route(routeFunction5)
                .build();
    }
}
