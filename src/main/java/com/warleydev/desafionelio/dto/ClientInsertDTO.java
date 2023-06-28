package com.warleydev.desafionelio.dto;

import com.warleydev.desafionelio.entities.Client;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.Instant;

public class ClientInsertDTO extends ClientDTO{



    public ClientInsertDTO(){
    }


    public ClientInsertDTO(Long id, String name, Double income, Instant birthDate, Integer children, String cpf) {
        super(id, name, income, birthDate, children, cpf);
    }

    public ClientInsertDTO(Client client) {
        super(client);
    }

}
