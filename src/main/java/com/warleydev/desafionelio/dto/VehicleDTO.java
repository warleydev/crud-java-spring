package com.warleydev.desafionelio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.warleydev.desafionelio.entities.Client;
import com.warleydev.desafionelio.entities.Vehicle;

public class VehicleDTO {
    private Long id;
    private String name;
    private Integer color;
    private Long ownerId;
    private String licensePlate;

    public VehicleDTO(){
    }

    public VehicleDTO(Long id, String name, Integer color, Long ownerId,String licensePlate) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.ownerId = ownerId;
        this.licensePlate = licensePlate;
    }

    public VehicleDTO(Vehicle entity){
        id = entity.getId();
        name = entity.getName();
        color = entity.getColor().getCode();
        ownerId = entity.getOwner().getId();
        licensePlate = entity.getLicensePlate();
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

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
