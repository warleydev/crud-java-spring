package com.warleydev.desafionelio.services;

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

import java.util.Objects;

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

    public Client insert(Client client){
        if (clientValidate(client)){
            return repository.save(client);
        }
        return null;
    }

    public Client update(Long id, Client updatedClient){
        if (repository.existsById(id)){
            Client suport = findById(id);
            if (updatedClient.getCpf() == null){
                updatedClient.setCpf(suport.getCpf());
            }
            NullField.nullFieldClient(updatedClient);

            if (Objects.equals(updatedClient.getCpf(), suport.getCpf())){
                updatedClient.setId(id);
                updatedClient = repository.save(updatedClient);
                return updatedClient;
            }
            cpfIsOk(updatedClient.getCpf());
            updatedClient.setId(id);
            updatedClient = repository.save(updatedClient);
            return updatedClient;
        }
        else throw new ResourceNotFoundException("Id "+id+" não encontrado");
    }

    public void deleteById(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
        }
        else throw new ResourceNotFoundException("Id "+id+" não encontrado!");
    }


    public boolean clientValidate(Client client){
        NullField.nullFieldClient(client);
        cpfIsOk(client.getCpf());
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

}
