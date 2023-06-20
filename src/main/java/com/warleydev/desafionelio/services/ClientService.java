package com.warleydev.desafionelio.services;

import com.warleydev.desafionelio.dto.ClientDTO;
import com.warleydev.desafionelio.dto.ClientInsertDTO;
import com.warleydev.desafionelio.dto.ClientUpdatedDTO;
import com.warleydev.desafionelio.entities.Client;
import com.warleydev.desafionelio.repositories.ClientRepository;
import com.warleydev.desafionelio.services.exceptions.InvalidCpfException;
import com.warleydev.desafionelio.services.exceptions.ResourceNotFoundException;
import com.warleydev.desafionelio.utils.IsCPF;
import com.warleydev.desafionelio.utils.NullField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<Client> findAll(PageRequest pageRequest){
        return repository.findAll(pageRequest);
    }

    @Transactional(readOnly = true)
    public Client findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));
    }

    public ClientInsertDTO insert(ClientInsertDTO dto){
        if (clientValidate(dto)){
            Client entity = new Client();
            fromDto(dto, entity);
            return new ClientInsertDTO(repository.save(entity), dto.getCpf());
        }
        return null;
    }

    public ClientUpdatedDTO update(Long id, ClientUpdatedDTO updatedDTO){
        if (repository.existsById(id)){
            Client entity = findById(id);

            NullField.nullFieldClientUpdate(updatedDTO);

            entity.setName(updatedDTO.getName());
            entity.setChildren(updatedDTO.getChildren());
            entity.setIncome(updatedDTO.getIncome());
            entity = repository.save(entity);

            return new ClientUpdatedDTO(entity);

        }
        else throw new ResourceNotFoundException("Id "+id+" não encontrado");
    }

    public void deleteById(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
        }
        else throw new ResourceNotFoundException("Id "+id+" não encontrado!");
    }


    public boolean clientValidate(ClientInsertDTO dto){
        NullField.nullFieldClientInsert(dto);
        cpfIsOk(dto.getCpf());
        return true;
    }

    public boolean cpfAlreadyRegistered(String cpf){
        return repository.existsByCpf(cpf);
    }

    public void cpfIsOk(String cpf){
        if (IsCPF.isValidcpf(cpf)) {
            if (cpfAlreadyRegistered(cpf)) {
                throw new InvalidCpfException("CPF '" + cpf + "' já existe.");
            }
        }
        else {
            throw new InvalidCpfException("CPF '"+cpf+"' inválido!");
        }
    }

    public void fromDto(ClientInsertDTO dto, Client entity){
        entity.setIncome(dto.getIncome());
        entity.setCpf(dto.getCpf());
        entity.setName(dto.getName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

}
