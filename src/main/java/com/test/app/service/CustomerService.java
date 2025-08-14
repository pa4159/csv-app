package com.test.app.service;

import com.test.app.model.Customer;
import com.test.app.components.CustomerReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class CustomerService {

    @Value("${api.customer.url}")
    private String url;

    @Autowired
    CustomerReader reader;

    public void postCustomers(File csv) throws IOException {
        List<Customer> customers = reader.readFile(csv);

        customers.forEach(customer -> {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Customer> request = new HttpEntity<>(customer);
            ResponseEntity<Customer> response = restTemplate
                    .exchange(url, HttpMethod.POST, request, Customer.class);

            HttpStatusCode statusCode = response.getStatusCode();
            if (statusCode.isError()) {
                log.error("POST to '{}' for customer reference '{}' failed with status code '{}'",
                        url, customer.getReference(), statusCode);
            }

            if (statusCode.is2xxSuccessful())
                log.debug("POST to '{}' for customer reference '{}' was successful. Status code '{}'",
                    url, customer.getReference(), statusCode);
        });
    }

}
