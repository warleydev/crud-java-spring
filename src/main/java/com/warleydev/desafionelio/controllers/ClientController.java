package com.warleydev.desafionelio.controllers;

import com.warleydev.desafionelio.dto.ClientDTO;
import com.warleydev.desafionelio.services.ClientService;
import com.warleydev.desafionelio.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "4") Integer pageSize,
        @RequestParam(defaultValue = "ASC") String direction,
        @RequestParam(defaultValue = "name") String sortBy
    ){
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.Direction.valueOf(direction), sortBy);
        Page<ClientDTO> listDto = service.findAll(pageRequest).map(x -> new ClientDTO(x));
        return ResponseEntity.ok(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        ClientDTO dto = new ClientDTO(service.findById(id));
        return ResponseEntity.ok(dto);
    }
}
