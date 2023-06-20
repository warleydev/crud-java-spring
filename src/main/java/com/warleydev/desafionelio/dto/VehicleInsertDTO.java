package com.warleydev.desafionelio.dto;

import com.warleydev.desafionelio.entities.Vehicle;

public class VehicleInsertDTO extends VehicleDTO{

    private String licensePlate;

    public VehicleInsertDTO(){
    }

    public VehicleInsertDTO(Long id, String name, Integer color, Long ownerId, String licensePlate) {
        super(id, name, color, ownerId);
        this.licensePlate = licensePlate;
    }

    public VehicleInsertDTO(Vehicle entity, String licensePlate) {
        super(entity);
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
