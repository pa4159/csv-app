package com.test.app;

import com.test.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Application implements CommandLineRunner {

    @Value("${csvFile}")
    private String csvFile;

    @Autowired
    CustomerService service;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        service.postCustomers(new File(csvFile));
    }
}