CREATE TABLE customers (
    customer_reference  VARCHAR(100)    NOT NULL,
    customer_name   VARCHAR(100),
    address_line_one    VARCHAR(200),
    address_line_two    VARCHAR(200),
    town    VARCHAR(100),
    county  VARCHAR(100),
    country VARCHAR(100),
    postcode    VARCHAR(50),
    CONSTRAINT pk_customers PRIMARY KEY (customer_reference)
);