package com.capstone.university.exceptions;

public class EnrollmentFullException extends RuntimeException{
    public EnrollmentFullException(Long id){
        super("Posti esauriti per il corso " + id);
    }
}
