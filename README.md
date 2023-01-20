# Spring Boot "products-micro-service" example project

This is a sample Java / Gradle / Spring Boot application that can be used as a starter for creating a microservice complete with built-in health check, metrics and much more.

## How to Run
* Clone this repository
* Make sure you are using JDK 11
* You can build the project and run the tests by running ```./gradlew build```
* Once successfully built, you can run the service (change %VERSION% to proper version from gradle build):
```
java -jar build/libs/products-micro-service-example-%VERSION%.jar
```

## About the Service

The service is just a simple products/threads service. It uses an in-memory database (H2) to store the data. You can also do with a relational database like MySQL or PostgreSQL. If your database connection properties work, you can adjust properties from application.properties file.

Operational endpoints are ```/products``` and ```/health``` (these are available on **port 8080**)

Here is what this application demonstrates:

* Full integration with the latest Spring Framework
* Creating a REST service using annotations
* Demonstrates how to set up healthcheck, metrics, info, environment, etc.
* Exception mapping from application exceptions to the right HTTP response with exception details in the body
* Spring Data Integration
* Demonstrates MockMVC test framework with associated libraries
* All APIs are "self-documented" by Swagger2 using annotations

Swagger url:
http://localhost:8080/swagger-ui/