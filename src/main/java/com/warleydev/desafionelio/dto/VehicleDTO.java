package com.warleydev.desafionelio.dto;

import com.warleydev.desafionelio.entities.Client;
import com.warleydev.desafionelio.entities.Vehicle;

public class VehicleDTO {
    private Long id;
    private String name;
    private String licensePlate;
    private Integer color;
    private Long ownerId;

    public VehicleDTO(){
    }

    public VehicleDTO(Long id, String name, String licensePlate, Integer color, Long ownerId) {
        this.id = id;
        this.name = name;
        this.licensePlate = licensePlate;
        this.color = color;
        this.ownerId = ownerId;
    }

    public VehicleDTO(Vehicle entity){
        id = entity.getId();
        name = entity.getName();
        licensePlate = entity.getLicensePlate();
        color = entity.getColor().getCode();
        ownerId = entity.getOwner().getId();
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

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}