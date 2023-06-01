package com.warleydev.desafionelio.services.exceptions;

public class InvalidLicensePlateException extends RuntimeException{
    public InvalidLicensePlateException(String msg){
        super(msg);
    }
}
