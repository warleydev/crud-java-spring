package com.warleydev.desafionelio.services.utils;

import com.warleydev.desafionelio.dto.ClientInsertDTO;
import com.warleydev.desafionelio.dto.ClientUpdateDTO;
import com.warleydev.desafionelio.dto.VehicleInsertDTO;
import com.warleydev.desafionelio.dto.VehicleUpdateDTO;
import com.warleydev.desafionelio.entities.enums.Color;
import com.warleydev.desafionelio.repositories.ClientRepository;
import com.warleydev.desafionelio.services.exceptions.UnderageClientException;
import com.warleydev.desafionelio.services.exceptions.NullOrEmptyFieldException;
import com.warleydev.desafionelio.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateObject {

    public static void nullFieldClientInsert(ClientInsertDTO client){
        if (client.getBirthDate() == null ||
                client.getChildren() == null || client.getIncome() == null || client.getCpf() == null){
            throw new NullOrEmptyFieldException("Preencha todos os campos!");
        }
    }

    public static void nullFieldClientUpdate(ClientUpdateDTO dto){
        if (dto.getName() == null || dto.getName() == "" || dto.getChildren() == null ||
                dto.getIncome() == null){
            throw new NullOrEmptyFieldException("Preencha todos os campos!");
        }
    }

    public static void nullFieldVehicle(VehicleInsertDTO vehicle){
        if (vehicle.getName() == null || vehicle.getName() == "" || vehicle.getColor() == null
                || vehicle.getLicensePlate() == null || vehicle.getOwnerId() == null){
            throw new NullOrEmptyFieldException("Cadastre todos os dados do veículo");
        }
        colorExists(vehicle.getColor());
    }

    public static void nullFieldVehicleUpdate(VehicleUpdateDTO vehicle){
        if (vehicle.getColor() == null || vehicle.getOwnerId() == null){
            throw new NullOrEmptyFieldException("Insira todos os dados do veículo");
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
