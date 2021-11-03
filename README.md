# Spring Boot and Kafka example

In this tutorial, we're gonna build a Spring Boot application with Kafka applying the concepts of Event-Driven Architecture, Producers, and Consumers. In this application, we are simulating one spring boot application **(PRODUCER)** that a customer can place an order and another application that simulate a transportation officer **(CONSUMER)**.
When the customer places an order the Transportation Officer consumes this information, using Apache Kafka and Event-Driven Architecture, to know if the order is ready for shipping, and can automatically collect the package.

### Technologies

> [Spring Boot](https://spring.io/projects/spring-boot)

> [Apache Kafka](https://kafka.apache.org/)

> [Apache Zookeeper](https://zookeeper.apache.org/)

> [Docker](https://www.docker.com/)

> [Java 8](https://www.java.com/en/download/help/java8_pt-br.html)

> [REST](https://en.wikipedia.org/wiki/Representational_state_transfer)

### Main Classes

- **OrderController** - Responsible to receive the POST request /orders (place a order) - See [REST CONTROLLER](https://spring.io/guides/gs/rest-service/)
- **OrderProducer** - Responsible to publish (write) a event in kafka topic (order-topic) - See [Kafka Documentation](https://kafka.apache.org/documentation/)
- **TransportationOfficerConsumer** - Responsible to subscribe the event(order-topic), read and process the event place order 


### Run Docker and Spring Boot applications

- Initialize Zookeeper and Kafka, in folder kafka-springboot-integration run:
 
```
docker-compose up
```

- Build the applications:
 
```
mvn clean package
```

- Initialize the projects CONSUMER AND PRODUCER:

```
cd producer
mvn spring-boot:run
```

```
cd consumer
mvn spring-boot:run
```

**The consumer project doesn't have an endpoint, it onlys conect to the order topic to listening the stream**

> Download [POSTMAN](https://www.postman.com/downloads/) for JSON requests.

### PLACE AN ORDER

- Open your postman application in **localhost:8080/orders**
- METHOD: **POST**
- BODY: raw - type **JSON**

```java
{
 "identifier":1,
 "customer": "CUSTOMER 1",
 "value": 1200,
 "address":"SQNW 309 bl. D apt 305",
 "zipcode": "70686456"
}
```
- RETURN - **200 OK**


### EXPECTED RESULTS - IN CONSOLE

- PRODUCER

```java
============= PRODUCER ======================
Receive the Order: 
{
 "identifier":1,
 "customer": "CUSTOMER 1",
 "value": 1200,
 "adress":"SQNW 309 bl. D apt 305",
 "zipcode": "70686456"
}
Process the payment: OK 

Packing: OK 

=============================================
```

- CONSUMER


```java
============= CONSUMER ======================
key: 0d2d5a8a-ead4-4c62-8dbd-da7d44319c89
Headers: RecordHeaders(headers = [], isReadOnly = false)
Partion: 0
Receive the Order: 
{
 "identifier":1,
 "customer": "CUSTOMER 1",
 "value": 1200,
 "adress":"SQNW 309 bl. D apt 305",
 "zipcode": "70686456"
}
Collect the package: OK 

Shipping the package: OK 

=============================================

```
 
