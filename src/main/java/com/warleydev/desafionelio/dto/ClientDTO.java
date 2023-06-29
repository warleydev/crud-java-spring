package com.warleydev.desafionelio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.warleydev.desafionelio.entities.Client;
import com.warleydev.desafionelio.entities.Vehicle;
import jakarta.validation.constraints.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

public class ClientDTO {
    private Long id;
    @NotBlank(message = "Você não tem nome? Este campo não pode estar vazio.")
    private String name;

    @PositiveOrZero(message = "Você está devendo por acaso? A renda não pode ser negativa.")
    @NotNull(message = "Este campo não pode estar vazio.")
    private Double income;
    private Integer age;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Past(message = "Você nasceu depois de hoje? Insira uma data válida.")
    @NotNull(message = "Você nunca nasceu? Este campo não pode estar vazio.")
    private Instant birthDate;

    @PositiveOrZero(message = "Você tem filhos negativos? Insira um valor maior ou igual a 0")
    @NotNull(message = "Este campo não pode estar vazio.")
    private Integer children;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String cpf;

    private Set<Long> vehiclesId = new HashSet<>();

    public ClientDTO(){
    }

    public ClientDTO(Long id, String name, Double income, Instant birthDate, Integer children, String cpf) {
        this.id = id;
        this.name = name;
        this.income = income;
        this.children = children;
        this.cpf = cpf;
        setAge(birthDate);
    }

    public ClientDTO(Client client){
        id = client.getId();
        name = client.getName();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
        cpf = client.getCpf();
        setAge(birthDate);
    }

    public ClientDTO(Client client, Set<Vehicle> vehicles){
        id = client.getId();
        name = client.getName();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
        cpf = client.getCpf();
        setAge(birthDate);
        vehicles.forEach(x -> vehiclesId.add(x.getId()));
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

    public Set<Long> getVehiclesId() {
        return vehiclesId;
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

    public String getCpf() {
        return cpf;
    }
}
