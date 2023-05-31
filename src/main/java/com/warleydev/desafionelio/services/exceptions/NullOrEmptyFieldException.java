package com.warleydev.desafionelio.services.exceptions;

public class NullOrEmptyFieldException extends RuntimeException{
    public NullOrEmptyFieldException(String msg){
        super(msg);
    }
}
