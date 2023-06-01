package com.warleydev.desafionelio.services;

import com.warleydev.desafionelio.dto.VehicleDTO;
import com.warleydev.desafionelio.entities.Vehicle;
import com.warleydev.desafionelio.entities.enums.Color;
import com.warleydev.desafionelio.repositories.ClientRepository;
import com.warleydev.desafionelio.repositories.VehicleRepository;
import com.warleydev.desafionelio.services.exceptions.InvalidLicensePlateException;
import com.warleydev.desafionelio.services.exceptions.NullOrEmptyFieldException;
import com.warleydev.desafionelio.services.exceptions.ResourceNotFoundException;
import com.warleydev.desafionelio.utils.LicensePlateValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<Vehicle> findAll(PageRequest pageRequest){
        return repository.findAll(pageRequest);
    }

    @Transactional(readOnly = true)
    public Vehicle findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado!"));
    }

    public VehicleDTO insert(VehicleDTO dto){

        if (vehicleValidate(dto)){
            Vehicle entity = new Vehicle(
                    null, dto.getName(), dto.getLicensePlate(), Color.valueOf(dto.getColor()),
                    clientRepository.getReferenceById(dto.getOwnerId()));
            return new VehicleDTO( repository.save(entity));
        }
        return null;
    }

    public Vehicle update(Long id, Vehicle updatedVehicle){
        if (repository.existsById(id)){
            updatedVehicle.setId(id);
            updatedVehicle = repository.save(updatedVehicle);
            return updatedVehicle;
        }
        else throw new ResourceNotFoundException("Id "+id+" não encontrado");
    }

    public void deleteById(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
        }
        else throw new ResourceNotFoundException("Id "+id+" não encontrado!");
    }

    public boolean vehicleValidate(VehicleDTO dto){
        if (dto.getName() == null || dto.getName() == "" || dto.getColor() == null
                || dto.getLicensePlate() == null || dto.getOwnerId() == null){
            throw new NullOrEmptyFieldException("Cadastre todos os dados do veículo");
        }
        if (!clientRepository.existsById(dto.getOwnerId())){
            throw new ResourceNotFoundException("Dono do veículo não encontrado! Id: "+ dto.getOwnerId());
        }

        if(LicensePlateValidate.isValid(dto.getLicensePlate())){
            if (plateAlreadyRegistered(dto.getLicensePlate())){
                throw new InvalidLicensePlateException("Placa '"+dto.getLicensePlate()+"' já existe!");
            }
        }
        else{
            throw new InvalidLicensePlateException("Modelo de placa inválido!");
        }
        return true;
    }

    public boolean plateAlreadyRegistered(String plate){
        return repository.existsByLicensePlate(plate);
    }

}
