# CSV App

This project populates a database with customer data, which it reads in from a CSV file. Each row of the data is sent
to an API POST endpoint in JSON format.

## Requirements

- Git
- Java 17+
- Maven

## Setup

Clone the repo:

```git clone https://github.com/pa4159/csv-app.git```

## Build

```mvn clean install```

Skip tests:

```mvn clean install -DskipTests```

## Local development

Spin up a PostgresDB container using `docker compose up`. You can then start the Spring Boot app to test your changes:

```mvn spring-boot:run```

By default, this reads data on start-up from: /src/main/resources/customers.csv.

## Endpoints

Create a new customer:
```POST /customer```

Get a customer by reference:
```GET /customer/:customerReference```

-- End of regular README --

## Approach/Thoughts

- Standard CRUD app using Spring Boot for ease of development (or technically just a CR app, in this case).
- Jackson Data Format CSV used to facilitate converting rows to POJOs. I did pause on this as the use of "loop" in the instructions made me wonder if you were after a manual implementation. I opted for simplicity as it's what I'd do at work, unless there were good reasons to avoid external dependencies, e.g. trying to reduce bloat.
- Honestly, my TDD was not the best on this application, mainly because it's very simple and most classes are lightweight.
- I got a bit carried away and added a testcontainers test, as I love the library and it saves you having to spin up the docker compose (if you'd like to run this).
