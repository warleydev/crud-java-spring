package com.warleydev.desafionelio.dto;

import com.warleydev.desafionelio.entities.Client;

import java.time.Instant;

public class ClientInsertDTO extends ClientDTO{

    private String cpf;

    public ClientInsertDTO(){
    }


    public ClientInsertDTO(Long id, String name, Double income, Instant birthDate, Integer children, String cpf) {
        super(id, name, income, birthDate, children);
        this.cpf = cpf;
    }

    public ClientInsertDTO(Client client, String cpf) {
        super(client);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
