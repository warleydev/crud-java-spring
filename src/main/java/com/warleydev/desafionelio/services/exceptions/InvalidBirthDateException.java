package com.warleydev.desafionelio.services.exceptions;

public class InvalidBirthDateException extends RuntimeException{
    public InvalidBirthDateException(String msg){
        super(msg);
    }
}
