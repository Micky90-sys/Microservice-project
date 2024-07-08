# Microservices Project

## Descrizione del Progetto

Questo progetto è un esempio di architettura a microservizi che include un server Eureka per la scoperta dei servizi, un gateway per la gestione delle richieste e vari microservizi per la gestione degli utenti, dei prodotti e dei pagamenti. Utilizza Spring Boot, Spring Cloud Netflix Eureka, Spring Cloud Gateway e JPA con H2 per il database in memoria.

## Struttura del Progetto

microservices-project/
│
├── eureka-server/
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/
│ │ │ │ └── com/example/eurekaserver/
│ │ │ │ └── EurekaServerApplication.java
│ │ │ ├── resources/
│ │ │ ├── application.properties
│ └── pom.xml
│
├── gateway-service/
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/
│ │ │ │ └── com/example/gatewayservice/
│ │ │ │ └── GatewayServiceApplication.java
│ │ │ ├── resources/
│ │ │ ├── application.properties
│ └── pom.xml
│
├── user-service/
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/
│ │ │ │ └── com/example/userservice/
│ │ │ │ ├── UserServiceApplication.java
│ │ │ │ ├── config/
│ │ │ │ │ └── SecurityConfig.java
│ │ │ │ ├── controller/
│ │ │ │ │ └── UserController.java
│ │ │ │ ├── entity/
│ │ │ │ │ └── User.java
│ │ │ │ ├── repository/
│ │ │ │ │ └── UserRepository.java
│ │ │ │ ├── security/
│ │ │ │ │ ├── CustomUserDetails.java
│ │ │ │ │ └── CustomUserDetailsService.java
│ │ │ │ └── service/
│ │ │ │ └── UserService.java
│ │ ├── resources/
│ │ ├── application.properties
│ └── test/
│ │ └── java/
│ │ └── com/example/userservice/
│ │ └── service/
│ │ └── UserServiceTest.java
│ └── pom.xml
│
├── product-service/
│ ├── src/
│ │ ├── main/
│ │ │ └── com/example/productservice/
│ │ │ ├── ProductServiceApplication.java
│ │ │ ├── controller/
│ │ │ │ └── ProductController.java
│ │ │ ├── entity/
│ │ │ │ └── Product.java
│ │ │ ├── repository/
│ │ │ │ └── ProductRepository.java
│ │ │ └── service/
│ │ │ └── ProductService.java
│ │ ├── resources/
│ │ ├── application.properties
│ └── test/
│ └── java/
│ └── com/example/productservice/
│ └── service/
│ └── ProductServiceTest.java
│ └── pom.xml
│
├── eureka-client/
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/
│ │ │ │ └── com/example/eurekaclient/
│ │ │ │ └── EurekaClientApplication.java
│ │ │ ├── resources/
│ │ │ ├── application.properties
│ └── pom.xml
│
└── payment-service/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/example/paymentservice/
│ │ │ ├── PaymentServiceApplication.java
│ │ │ ├── controller/
│ │ │ │ └── PaymentController.java
│ │ ├── resources/
│ │ ├── application.properties
│ └── test/
│ └── java/
│ └── com/example/paymentservice/
│ └── service/
│ └── PaymentServiceTest.java
└── pom.xml

## Microservizi

### Eureka Server

Il server Eureka è responsabile della registrazione e della scoperta degli altri microservizi.

- **Applicazione Principale**: `EurekaServerApplication.java`
- **Configurazione**: `application.properties`

#### Esempio di `application.properties`

```properties
spring.application.name=eureka-server
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false


Gateway Service
Il gateway è il punto di ingresso per tutte le richieste verso i microservizi. Utilizza Spring Cloud Gateway per instradare le richieste ai microservizi appropriati.

Applicazione Principale: GatewayServiceApplication.java
Configurazione: application.properties

Esempio di application.properties:

spring.application.name=gateway-service
server.port=8080
spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lower-case-service-id=true
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

User Service
Il servizio utente gestisce le operazioni CRUD per gli utenti.

Applicazione Principale: UserServiceApplication.java
Controller: UserController.java
Entità: User.java
Repository: UserRepository.java
Service: UserService.java
Configurazione: application.properties

Esempio di application.properties:

spring.application.name=user-service
server.port=8081
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

Product Service
Il servizio prodotto gestisce le operazioni CRUD per i prodotti.

Applicazione Principale: ProductServiceApplication.java
Controller: ProductController.java
Entità: Product.java
Repository: ProductRepository.java
Service: ProductService.java
Configurazione: application.properties

Esempio di application.properties:

spring.application.name=product-service
server.port=8082
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/


Eureka Client
Il client Eureka è un esempio di servizio che si registra con il server Eureka.

Applicazione Principale: EurekaClientApplication.java
Configurazione: application.properties

Esempio di application.properties:

spring.application.name=eureka-client
server.port=8083
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/


Payment Service
Il servizio di pagamento gestisce le operazioni di pagamento.

Applicazione Principale: PaymentServiceApplication.java
Controller: PaymentController.java
Configurazione: application.properties

Esempio di application.properties:

spring.application.name=payment-service
server.port=8084
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/


Come Avviare il Progetto

Avviare il server Eureka:

cd eureka-server
mvn spring-boot:run

Avviare il gateway:

cd gateway-service
mvn spring-boot:run

Avviare il servizio utente:

cd user-service
mvn spring-boot:run

Avviare il servizio prodotto:

cd product-service
mvn spring-boot:run

Avviare il client Eureka:

cd eureka-client
mvn spring-boot:run

Avviare il servizio di pagamento:

cd payment-service
mvn spring-boot:run

Come Testare il Progetto

Test degli Endpoint

Recuperare tutti gli utenti:

curl -X GET http://localhost:8080/api/users

Creare un nuovo utente:

**sh

curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d '{"name": "John Doe", "email": "john.doe@example.com"}'


Eliminare un utente:

**sh

curl -X DELETE http://localhost:8080/api/users/1


Recuperare tutti i prodotti:

**sh

curl -X GET http://localhost:8080/api/products

Creare un nuovo prodotto:

**sh

curl -X POST http://localhost:8080/api/products -H "Content-Type: application/json" -d '{"name": "Product Name", "price": 100.0}'


Eliminare un prodotto:

**sh

curl -X DELETE http://localhost:8080

## Conclusioni

Questo progetto dimostra come implementare un'architettura a microservizi utilizzando Spring Boot, Spring Cloud Netflix Eureka e Spring Cloud Gateway. Ogni servizio è indipendente e può essere scalato separatamente, migliorando la manutenibilità e la scalabilità del sistema complessivo.