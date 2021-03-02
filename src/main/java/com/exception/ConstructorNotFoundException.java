package com.exception;

public class ConstructorNotFoundException extends Exception {
    public ConstructorNotFoundException(String message) {
        System.out.println(message);
    }
}
