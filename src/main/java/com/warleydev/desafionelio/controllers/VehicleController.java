package com.warleydev.desafionelio.controllers;

import com.warleydev.desafionelio.dto.VehicleInsertDTO;
import com.warleydev.desafionelio.dto.VehicleUpdateDTO;
import com.warleydev.desafionelio.entities.Vehicle;
import com.warleydev.desafionelio.dto.VehicleDTO;
import com.warleydev.desafionelio.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @GetMapping
    public ResponseEntity<Page<VehicleDTO>> findAll(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "4") Integer pageSize,
        @RequestParam(defaultValue = "ASC") String direction,
        @RequestParam(defaultValue = "id") String sortBy
    ){
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.Direction.valueOf(direction), sortBy);
        Page<VehicleDTO> listDto = service.findAll(pageRequest).map(x -> new VehicleDTO(x));
        return ResponseEntity.ok(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable Long id){
        VehicleDTO dto = new VehicleDTO(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<VehicleInsertDTO> insert(@RequestBody VehicleInsertDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VehicleUpdateDTO> update(@PathVariable Long id, @RequestBody VehicleUpdateDTO dto){
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
