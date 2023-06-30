package com.warleydev.desafionelio.dto;

import com.warleydev.desafionelio.entities.Vehicle;
import jakarta.validation.constraints.NotBlank;

public class VehicleInsertDTO extends VehicleDTO{

    @NotBlank(message = "Carro sem nome?? Este campo não pode estar vazio.")
    private String name;

    public VehicleInsertDTO(){
    }

    public VehicleInsertDTO(Long id, String name, Integer color, Long ownerId, String licensePlate) {
        super(id, name, color, ownerId, licensePlate);
    }

    public VehicleInsertDTO(Vehicle entity) {
        super(entity);
    }
}
