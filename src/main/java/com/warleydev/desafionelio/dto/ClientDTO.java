package com.warleydev.desafionelio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.warleydev.desafionelio.entities.Client;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

public class ClientDTO {
    private Long id;
    @NotBlank
    private String name;
    private Double income;
    private Integer age;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Instant birthDate;
    private Integer children;

    private Set<VehicleDTO> vehicles = new HashSet<>();

    public ClientDTO(){
    }

    public ClientDTO(Long id, String name, Double income, Instant birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.income = income;
        this.children = children;
        setAge(birthDate);
    }

    public ClientDTO(Client client){
        id = client.getId();
        name = client.getName();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
        setAge(birthDate);
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

    public Set<VehicleDTO> getVehicles() {
        return vehicles;
    }

    public final Integer getAge() {
        return age;
    }

    public final void setAge(Instant birthDate) {
        LocalDate birthLocalDate = birthDate.atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(birthLocalDate, LocalDate.now());
        age = period.getYears();
    }

    public Instant getBirthDate() {
        return birthDate;
    }
}
