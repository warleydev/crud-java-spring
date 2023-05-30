package com.warleydev.desafionelio.services.exceptions;

public class InvalidCpfException extends RuntimeException{
    public InvalidCpfException(String msg){
        super(msg);
    }
}
