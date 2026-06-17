package com.example.demo.errors;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Long id) {
        super("Could not find student " + id);
    }

}
