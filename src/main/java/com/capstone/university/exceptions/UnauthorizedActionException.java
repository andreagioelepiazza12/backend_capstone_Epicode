package com.capstone.university.exceptions;

public class UnauthorizedActionException extends RuntimeException{
    public UnauthorizedActionException(String message) {
        super(message);
    }
}
