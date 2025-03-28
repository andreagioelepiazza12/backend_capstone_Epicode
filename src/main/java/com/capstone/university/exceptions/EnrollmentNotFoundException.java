package com.capstone.university.exceptions;

public class EnrollmentNotFoundException extends RuntimeException{
    public EnrollmentNotFoundException(String message) {
        super(message);
    }
}
