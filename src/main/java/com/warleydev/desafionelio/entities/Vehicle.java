package com.warleydev.desafionelio.entities;

import com.warleydev.desafionelio.entities.enums.Color;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "tb_vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String licensePlate;
    private Integer color;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private Client owner;

    public Vehicle(){
    }

    public Vehicle(Long id, String name, String licensePlate, Color color, Client owner){
        this.id = id;
        this.name = name;
        this.licensePlate = licensePlate;
        setColor(color.getCode());
        this.owner = owner;
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

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Color getColor() {
        return Color.valueOf(color);
    }

    public void setColor(Integer color) {

        this.color = color;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
