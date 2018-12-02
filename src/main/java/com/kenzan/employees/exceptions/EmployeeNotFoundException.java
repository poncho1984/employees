package com.kenzan.employees.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    private static final String MESSAGE = "The request employee don´t exist or is inactive.";

    public EmployeeNotFoundException() {
        super(MESSAGE);
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }

}
