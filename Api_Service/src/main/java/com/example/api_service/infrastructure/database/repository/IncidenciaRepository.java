package com.example.api_service.infrastructure.database.repository;

import com.example.api_service.infrastructure.database.entities.Incidencia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenciaRepository extends MongoRepository<Incidencia, String> {
}
