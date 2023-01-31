package com.example.api_service.infrastructure.database.repository;

import com.example.api_service.infrastructure.database.entities.Client;
import com.example.api_service.infrastructure.database.entities.Incidencia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenciaRepository extends MongoRepository<Incidencia, String> {
    List<Incidencia> findByClient(Client client);
}
