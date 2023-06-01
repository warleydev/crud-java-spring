package com.warleydev.desafionelio.repositories;

import com.warleydev.desafionelio.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    public boolean existsByCpf(String cpf);
}
