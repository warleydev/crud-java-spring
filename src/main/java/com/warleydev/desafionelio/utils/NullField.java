package com.warleydev.desafionelio.utils;

import com.warleydev.desafionelio.entities.Client;
import com.warleydev.desafionelio.services.exceptions.NullOrEmptyFieldException;

public class NullField {

    public static void nullFieldClient(Client client){
        if (client.getName() == null || client.getName() == "" || client.getBirthDate() == null ||
                client.getChildren() == null || client.getIncome() == null || client.getCpf() == null){
            throw new NullOrEmptyFieldException("Preencha todos os campos!");
        }
    }
}
