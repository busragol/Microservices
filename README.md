# Microservices Demo</br>
It contains examples of api-gateway, config-server, eureka-server, feign-client.</br></br>
I will try to explain these terms. The Demo project can be more understandable with these explanations.

## 1. FeignClient</br>
RestTemplate can be used to establish communication between microservices but FeignClient was used here given that it has many advantages.
* Easy to use
* Cleaner code
* Microservice called is like a service in the current project
* Information about microservice at one place, easy to manage
  
FeignClient eases synchronous communication between microservices and uses HTTP protocol. When using FeignClient, we must wait for the response.
Therefore FeignClient is not suitable for real-time applications. Kafka, WebSocket, gRPC can be used for real-time applications.
  
## 2. Eureka Server</br>
Eureka server is a component in the microservice architecture and microservices register to eureka server and then they are called eureka-client. 
Eureka server provide information about service's instances, name, address, port and whether the instances are up or down.
If a microservice registers to the eureka-server, it is easy to find and communicate.
Eureka server can perform load balancing. If an instance of service has too much load, eureka server redirects requests to another instance.
However, eureka server is not exactly a load balancer because it doesn't have detailed features typically found in dedicated load balancing solutions designed as load balancers.
For instance, Eureka server doesn't offer capabilities such as configuring traffic routing strategies or distributing load based on specific performance metrics. 
Instead, Eureka server simply provides basic traffic balancing among registered service instances.
Eureka server can has many running instances and this provides high availability.

## 3. Config Server</br>
By using Config Server, we can manage configurations of microservices from a central location. This provides some advantages:</br>
### * Centralized Management:</br>
It allows for consistency and ensures that all instances of an application share the same configuration. 
This reduces the risk of configuration drift and simplifies maintenance.
### * Ease of Updates:</br>
It makes updating configurations accross multiple environments like development, testing, production easy without the need to modify code or configurations in each environment separately.
### * Version Control:</br>
Configurations can be stored in a central repository like Git. It allows to track changes, reviewedand rolled back if it is necessary.
This provides an audit trail and helps ensure accountability.
### * Security and Access Control:</br>
Centralized configuration management allows for implementing security measures, such as access control and encryption, to protect sensitive configuration data.
Access to configurations can be restricted to authorized users or applications.
### * Simplified Deployment:</br>
Applications can retrieve configurations dynamically from a centralized source at runtime, reducing the need for hardcoding configuration values in code or packaging configuration files with the application.
This simplfies deployment and promotes scalability.
### * Consistency Across Environments:</br>
Environments can be configured consistently, ensuring that applications behave predictably across different environments.

## 4. Api Gateway</br>
