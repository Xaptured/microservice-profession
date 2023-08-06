package com.thejackfolio.microservices.professionapi.exceptions;

public class ValidationException extends Exception{

    public ValidationException(String message){
        super(message);
    }
}
