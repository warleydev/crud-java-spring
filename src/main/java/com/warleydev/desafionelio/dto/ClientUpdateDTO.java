package com.warleydev.desafionelio.dto;

import com.warleydev.desafionelio.entities.Client;

import java.time.Instant;

public class ClientUpdateDTO extends ClientDTO{

    public ClientUpdateDTO() {
    }

    public ClientUpdateDTO(Long id, String name, Double income, Instant birthDate, Integer children, String cpf) {
        super(id, name, income, birthDate, children, cpf);
    }

    public ClientUpdateDTO(Client client) {
        super(client);
    }

    @Override
    public String getCpf() {
        return null;
    }
}
