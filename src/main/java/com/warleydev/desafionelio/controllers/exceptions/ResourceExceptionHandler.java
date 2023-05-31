package com.warleydev.desafionelio.controllers.exceptions;

import com.warleydev.desafionelio.services.exceptions.InvalidCpfException;
import com.warleydev.desafionelio.services.exceptions.NullOrEmptyFieldException;
import com.warleydev.desafionelio.services.exceptions.OwnerNotFoundException;
import com.warleydev.desafionelio.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ResourceNotFoundException e, HttpServletRequest request){
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Objeto não encontrado!");
        err.setPath(request.getRequestURI());
        err.setMessage(e.getMessage());
        return ResponseEntity.status(err.getStatus()).body(err);
    }

    @ExceptionHandler(InvalidCpfException.class)
    public ResponseEntity<StandardError> invalidCpf(InvalidCpfException e, HttpServletRequest request){
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("CPF informado é inválido!");
        err.setPath(request.getRequestURI());
        err.setMessage(e.getMessage());
        return ResponseEntity.status(err.getStatus()).body(err);
    }
    @ExceptionHandler(NullOrEmptyFieldException.class)
    public ResponseEntity<StandardError> emptyOrNullField(NullOrEmptyFieldException e, HttpServletRequest request){
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Você precisa preencher todos os dados!");
        err.setPath(request.getRequestURI());
        err.setMessage(e.getMessage());
        return ResponseEntity.status(err.getStatus()).body(err);
    }
    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<StandardError> ownerlessVehicle(OwnerNotFoundException e, HttpServletRequest request){
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Um veículo precisa de um dono!");
        err.setPath(request.getRequestURI());
        err.setMessage(e.getMessage());
        return ResponseEntity.status(err.getStatus()).body(err);
    }
}
