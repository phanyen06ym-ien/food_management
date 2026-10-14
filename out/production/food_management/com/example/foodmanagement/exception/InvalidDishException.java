package com.example.foodmanagement.exception;

public class InvalidDishException extends RuntimeException {

    private final String field;

    public InvalidDishException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
