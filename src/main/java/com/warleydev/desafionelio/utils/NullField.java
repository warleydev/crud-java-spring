package com.warleydev.desafionelio.utils;

import com.warleydev.desafionelio.dto.VehicleDTO;
import com.warleydev.desafionelio.entities.Client;
import com.warleydev.desafionelio.entities.Vehicle;
import com.warleydev.desafionelio.entities.enums.Color;
import com.warleydev.desafionelio.services.exceptions.NullOrEmptyFieldException;
import com.warleydev.desafionelio.services.exceptions.ResourceNotFoundException;

public class NullField {

    public static void nullFieldClient(Client client){
        if (client.getName() == null || client.getName() == "" || client.getBirthDate() == null ||
                client.getChildren() == null || client.getIncome() == null || client.getCpf() == null){
            throw new NullOrEmptyFieldException("Preencha todos os campos!");
        }
    }

    public static void nullFieldVehicle(VehicleDTO vehicle){
        if (vehicle.getName() == null || vehicle.getName() == "" || vehicle.getColor() == null
                || vehicle.getLicensePlate() == null || vehicle.getOwnerId() == null){
            throw new NullOrEmptyFieldException("Cadastre todos os dados do veículo");
        }
        colorExists(vehicle.getColor());
    }

    static void colorExists (int code){
        for(Color value : Color.values()){
            if (value.getCode() == code){
                return;
            }
        }
        throw new ResourceNotFoundException("Cor inválida!");
    }
}
