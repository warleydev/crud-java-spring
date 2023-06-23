package com.warleydev.desafionelio.dto;

import com.warleydev.desafionelio.entities.Vehicle;

public class VehicleUpdateDTO extends VehicleDTO{

    public VehicleUpdateDTO() {
    }

    public VehicleUpdateDTO(Long id, String name, Integer color, Long ownerId, String licensePlate) {
        super(id, name, color, ownerId, licensePlate);
    }

    public VehicleUpdateDTO(Vehicle entity) {
        super(entity);
    }

    @Override
    public String getLicensePlate(){
        return null;
    }
}
