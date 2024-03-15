# Microservices Demo</br>
It contains examples of api-gateway, config-server, eureka-server, feign-client.</br></br>
**Explanation:**</br></br>
**1. FeignClient:**</br>
RestTemplate can be used to establish communication between microservices but FeignClient was used here given that it has many advantages.
* Easy to use
* Cleaner code
* Microservice called is like a service in the current project
* Information about microservice at one place, easy to manage
  
FeignClient eases synchronous communication between microservices and uses HTTP protocol. When using FeignClient, we must wait for the response.
Therefore FeignClient is not suitable for real-time applications. Kafka, WebSocket, gRPC can be used for real-time applications.
  
**2. Eureka Server:**</br>
Eureka server is a component in the microservice architecture and microservices register to eureka server and then they are called eureka-client. 
Eureka server provide information about service's instances, name, address, port and whether the instances are up or down.
If a microservice registers to the eureka-server, it is easy to find and communicate.
Eureka server can perform load balancing. If an instance of service has too much load, eureka server redirects requests to another instance.
However, eureka server is not exactly a load balancer because it doesn't have detailed features typically found in dedicated load balancing solutions designed as load balancers.
For instance, Eureka server doesn't offer capabilities such as configuring traffic routing strategies or distributing load based on specific performance metrics. 
Instead, Eureka server simply provides basic traffic balancing among registered service instances.
Eureka server can has many running instances and this provides high availability.

**3. Config Server:**</br>
**4. Api GAteway:**</br>
