package com.warleydev.desafionelio.dto;

import com.warleydev.desafionelio.entities.Client;

import java.util.Objects;
import java.util.Set;

public class ClientUpdateDTO {
    private Long id;
    private String name;
    private Double income;
    private Integer children;
    public ClientUpdateDTO(){
    }

    public ClientUpdateDTO(Long id, String name, Double income, Integer children, Set<VehicleDTO> vehicles) {
        this.id = id;
        this.name = name;
        this.income = income;
        this.children = children;
    }

    public ClientUpdateDTO(Client client){
        id = client.getId();
        name = client.getName();
        income = client.getIncome();
        children = client.getChildren();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientUpdateDTO that = (ClientUpdateDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
