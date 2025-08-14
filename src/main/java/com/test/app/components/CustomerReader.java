package com.test.app.components;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.test.app.model.Customer;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Component
public class CustomerReader {

    private static final CsvMapper CSV_MAPPER = new CsvMapper();
    private static final CsvSchema CUSTOMER_SCHEMA = CSV_MAPPER.schemaFor(Customer.class);

    public List<Customer> readFile(File csv) throws IOException {
        MappingIterator<Customer> customerIterator = CSV_MAPPER
                .readerFor(Customer.class)
                .with(CUSTOMER_SCHEMA)
                .readValues(Files.readString(csv.toPath()));

        List<Customer> customers = customerIterator.readAll();

        return sanitise(customers);
    }

    private List<Customer> sanitise(List<Customer> customers) {
        return customers.stream()
                .distinct()
                .filter(customer -> !customer.getReference().isEmpty())
                .toList();
    }
}
