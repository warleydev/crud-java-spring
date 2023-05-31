package com.warleydev.desafionelio.services;

import com.warleydev.desafionelio.dto.VehicleDTO;
import com.warleydev.desafionelio.entities.Client;
import com.warleydev.desafionelio.entities.Vehicle;
import com.warleydev.desafionelio.entities.enums.Color;
import com.warleydev.desafionelio.repositories.ClientRepository;
import com.warleydev.desafionelio.repositories.VehicleRepository;
import com.warleydev.desafionelio.services.exceptions.InvalidCpfException;
import com.warleydev.desafionelio.services.exceptions.NullOrEmptyFieldException;
import com.warleydev.desafionelio.services.exceptions.OwnerNotFoundException;
import com.warleydev.desafionelio.services.exceptions.ResourceNotFoundException;
import com.warleydev.desafionelio.utils.IsCPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EmptyStackException;

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
        if (dto.getOwnerId() == null){
            throw new OwnerNotFoundException("Adicione um dono a este veículo");
        }
        if (vehicleValidate(dto)){
            throw new NullOrEmptyFieldException("TESTEEEEEEEEEEEEEEE");
        }

        if (!clientRepository.existsById(dto.getOwnerId())){
            throw new ResourceNotFoundException("Dono do veículo não encontrado! Id: "+ dto.getOwnerId());
        }
        Vehicle entity = new Vehicle(
                null, dto.getName(), dto.getLicensePlate(), Color.valueOf(dto.getColor()),
                clientRepository.getReferenceById(dto.getOwnerId()));
        return new VehicleDTO( repository.save(entity));

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

    public boolean vehicleValidate(VehicleDTO vehicle){
        if (vehicle.getName() == null || vehicle.getName() == "" || vehicle.getOwnerId() == null
                || vehicle.getColor() == null || vehicle.getLicensePlate() == null){
            return true;
        }
        return false;
    }
}
