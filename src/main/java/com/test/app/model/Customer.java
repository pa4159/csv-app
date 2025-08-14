package com.test.app.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@Table(name = "customers")
//@JsonSerialize
//@JsonDeserialize
@JsonPropertyOrder( {"reference", "name", "addressLineOne", "addressLineTwo", "town", "county", "country", "postcode"})
public class Customer {

    @Id
    @Column(name = "customer_reference", nullable = false)
    private String reference;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "address_line_one")
    private String addressLineOne;

    @Column(name = "address_line_two")
    private String addressLineTwo;

    @Column(name = "town")
    private String town;

    @Column(name = "county")
    private String county;

    @Column(name = "country")
    private String country;

    @Column(name = "postcode")
    private String postcode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(reference, customer.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }
}