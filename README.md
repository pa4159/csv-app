# CSV App

This project populates a database with customer data, which it reads in from a CSV file. Each row of the data is sent
to an API POST endpoint in JSON format.

## Requirements

- Git
- Java 17+
- Maven

## Setup

Clone the repo:

```git clone ```

## Build

```mvn clean install```

Skip tests:

```mvn clean install -DskipTests```

## Local development

Spin up a PostgresDB container using `docker compose up`. You can then start the Spring Boot app to test your changes:

```mvn spring-boot:run````

By default, this reads data on start-up from: /src/main/resources/customers.csv.

## Endpoints

Create a new customer:
```POST /customer```

Get a customer by reference:
```GET /customer/:customerReference```