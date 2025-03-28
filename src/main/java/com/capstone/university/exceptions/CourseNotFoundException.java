package com.capstone.university.exceptions;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException (Long id){
        super("Corso non trovato con ID: " + id);
    }
}
