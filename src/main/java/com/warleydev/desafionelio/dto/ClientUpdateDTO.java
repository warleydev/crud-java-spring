package com.warleydev.desafionelio.dto;

import com.warleydev.desafionelio.entities.Client;

import java.time.Instant;

public class ClientUpdateDTO extends ClientDTO{

    public ClientUpdateDTO() {
    }

    public ClientUpdateDTO(Long id, String name, Double income, Instant birthDate, Integer children) {
        super(id, name, income, birthDate, children);
    }

    public ClientUpdateDTO(Client client) {
        super(client);
    }

}
