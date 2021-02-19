package com.example.ProjectLekomat.exception;

public class NameNotFoundException extends RuntimeException {

    public NameNotFoundException(String name){
        super("Customer : " + name + " not found");

    }
}
