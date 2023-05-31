package com.warleydev.desafionelio.services.exceptions;

public class OwnerNotFoundException extends RuntimeException{
    public OwnerNotFoundException(String msg){
        super(msg);
    }
}
