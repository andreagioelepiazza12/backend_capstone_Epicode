package com.capstone.university.exceptions;

public class OperationNotAllowedException extends RuntimeException{
    public OperationNotAllowedException(String message){
        super(message);
    }
}
