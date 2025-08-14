package com.test.app.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String reference) {
        super("Unable to find customer with reference: " + reference);
    }
}
