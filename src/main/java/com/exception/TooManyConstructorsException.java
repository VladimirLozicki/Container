package com.exception;

public class TooManyConstructorsException extends Exception {
    public TooManyConstructorsException(String message) {
        System.out.println(message);
    }
}
