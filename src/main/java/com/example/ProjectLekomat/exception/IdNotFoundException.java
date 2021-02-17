package com.example.ProjectLekomat.exception;

public class IdNotFoundException extends RuntimeException {


    public IdNotFoundException(long id){
        super("Id: " + id + " not found");

    }
}
