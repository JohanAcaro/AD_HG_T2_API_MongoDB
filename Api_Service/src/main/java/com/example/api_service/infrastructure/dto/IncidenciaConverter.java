package com.example.api_service.infrastructure.dto;

import com.example.api_service.infrastructure.database.entities.Incidencia;

@FunctionalInterface
public interface IncidenciaConverter {

    Incidencia build();

    static IncidenciaConverter convertFrom(IncidenciaDto incidenciaDto) {
        return () -> new Incidencia(
                incidenciaDto.client(),
                incidenciaDto.fecha(),
                incidenciaDto.motivo(),
                incidenciaDto.tipoIncidencia(),
                incidenciaDto.tipoReparacion(),
                incidenciaDto.solucionado()
        );
    }
}
