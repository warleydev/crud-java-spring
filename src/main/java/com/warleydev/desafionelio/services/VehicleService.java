package com.warleydev.desafionelio.services;

import com.warleydev.desafionelio.dto.VehicleInsertDTO;
import com.warleydev.desafionelio.dto.VehicleUpdateDTO;
import com.warleydev.desafionelio.entities.Vehicle;
import com.warleydev.desafionelio.entities.enums.Color;
import com.warleydev.desafionelio.repositories.ClientRepository;
import com.warleydev.desafionelio.repositories.VehicleRepository;
import com.warleydev.desafionelio.services.exceptions.InvalidLicensePlateException;
import com.warleydev.desafionelio.services.exceptions.UnderageClientException;
import com.warleydev.desafionelio.services.exceptions.ResourceNotFoundException;
import com.warleydev.desafionelio.services.utils.LicensePlateValidate;
import com.warleydev.desafionelio.services.utils.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.warleydev.desafionelio.services.utils.ValidateObject.*;

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

    public VehicleInsertDTO insert(VehicleInsertDTO dto){

        if (vehicleValidate(dto)){
            Vehicle entity = new Vehicle(
                    null, dto.getName(), dto.getLicensePlate(), Color.valueOf(dto.getColor()),
                    clientRepository.getReferenceById(dto.getOwnerId()));
            return new VehicleInsertDTO(repository.save(entity));
        }
        return null;
    }

    public VehicleUpdateDTO update(Long id, VehicleUpdateDTO updatedVehicle){
        if (repository.existsById(id)){
            nullFieldVehicleUpdate(updatedVehicle);
            clientCanHaveCar(updatedVehicle.getOwnerId());
            Vehicle entity = findById(id);
            saveVehicle(updatedVehicle, entity);
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


    public boolean vehicleValidate(VehicleInsertDTO dto){
        ValidateObject.nullFieldVehicle(dto);
        if (!clientRepository.existsById(dto.getOwnerId())){
            throw new ResourceNotFoundException("Dono do veículo não encontrado! Id: "+ dto.getOwnerId());
        }
        clientCanHaveCar(dto.getOwnerId());
        plateIsOk(dto.getLicensePlate());
        return true;
    }

    public void plateIsOk(String plate){
        if(LicensePlateValidate.isValid(plate)){
            if (repository.existsByLicensePlate(plate)){
                throw new InvalidLicensePlateException("Placa '"+plate+"' já existe!");
            }
        }
        else{
            throw new InvalidLicensePlateException("Modelo de placa inválido!");
        }
    }

    public void updateData(VehicleUpdateDTO dto, Vehicle entity){
        if (!clientRepository.existsById(dto.getOwnerId())){
            throw new ResourceNotFoundException("Dono do veículo não encontrado! Id: "+ dto.getOwnerId());
        }
        entity.setColor(dto.getColor());
        entity.setOwner(clientRepository.getReferenceById(dto.getOwnerId()));
    }

    void saveVehicle(VehicleUpdateDTO dto, Vehicle entity){
        updateData(dto, entity);
        entity = repository.saveAndFlush(entity);
    }

    void clientCanHaveCar(Long id){
        if (clientRepository.findById(id).get().getAge() >= 18){
            return;
        }
        throw new UnderageClientException("O cliente precisa ter ao menos 18 anos.");
    }
}
