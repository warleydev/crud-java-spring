package com.warleydev.desafionelio.dto;

import com.warleydev.desafionelio.entities.Client;

import java.time.Instant;

public class ClientInsertDTO{
    private Long id;
    private String name;
    private Double income;
    private Integer children;
    private String cpf;
    private Instant birthDate;

    public ClientInsertDTO(){
    }

    public ClientInsertDTO(Long id, String name, Double income, Integer children, String cpf, Instant birthDate) {
        this.id = id;
        this.name = name;
        this.income = income;
        this.children = children;
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public ClientInsertDTO(Client client){
        id = client.getId();
        name = client.getName();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
        cpf = client.getCpf();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }
}
