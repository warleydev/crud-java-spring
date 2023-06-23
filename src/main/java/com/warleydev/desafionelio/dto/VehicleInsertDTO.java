package com.warleydev.desafionelio.dto;

import com.warleydev.desafionelio.entities.Vehicle;

public class VehicleInsertDTO extends VehicleDTO{

    public VehicleInsertDTO(){
    }

    public VehicleInsertDTO(Long id, String name, Integer color, Long ownerId, String licensePlate) {
        super(id, name, color, ownerId, licensePlate);
    }

    public VehicleInsertDTO(Vehicle entity) {
        super(entity);
    }
}
