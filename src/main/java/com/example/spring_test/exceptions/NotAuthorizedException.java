package com.example.spring_test.exceptions;

public class NotAuthorizedException extends RuntimeException{
    public NotAuthorizedException() {
        super("Not authorized");
    }
    public NotAuthorizedException(String message) {
        super(message);
    }
}
