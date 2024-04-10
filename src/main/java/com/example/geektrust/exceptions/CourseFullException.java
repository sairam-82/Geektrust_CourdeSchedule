package com.example.geektrust.exceptions;

public class CourseFullException extends RuntimeException {
    public CourseFullException(String msg){
        super(msg);
    }
}
