package com.warleydev.desafionelio.repositories;

import com.warleydev.desafionelio.entities.Client;
import com.warleydev.desafionelio.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
