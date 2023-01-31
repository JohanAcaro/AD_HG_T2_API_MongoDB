package com.example.api_service.infrastructure.controller;

import com.example.api_service.infrastructure.database.repository.IncidenciaRepository;
import com.example.api_service.infrastructure.dto.IncidenciaConverter;
import com.example.api_service.infrastructure.dto.IncidenciaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncidenciaController {

    private final IncidenciaRepository incidenciaRepository;

    public IncidenciaController(IncidenciaRepository incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody IncidenciaDto incidenciaDto) {
        var incidencia = IncidenciaConverter
                .convertFrom(incidenciaDto)
                .build();
        var rs = incidenciaRepository.save(incidencia);
        return ResponseEntity.ok("Incidencia insertada con id: " + rs.getId());
    }

}
