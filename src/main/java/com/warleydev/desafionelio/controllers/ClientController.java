package com.warleydev.desafionelio.controllers;

import com.warleydev.desafionelio.dto.ClientDTO;
import com.warleydev.desafionelio.dto.ClientInsertDTO;
import com.warleydev.desafionelio.dto.ClientUpdateDTO;
import com.warleydev.desafionelio.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
        @RequestParam(defaultValue = "id") String sortBy
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

    @PostMapping
    public ResponseEntity<ClientInsertDTO> insert(@Valid  @RequestBody ClientInsertDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientUpdateDTO> update(@PathVariable Long id, @RequestBody ClientUpdateDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
