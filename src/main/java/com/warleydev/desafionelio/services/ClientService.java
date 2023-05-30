package com.warleydev.desafionelio.services;

import com.warleydev.desafionelio.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.warleydev.desafionelio.repositories.ClientRepository;
import com.warleydev.desafionelio.entities.Client;

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


}
