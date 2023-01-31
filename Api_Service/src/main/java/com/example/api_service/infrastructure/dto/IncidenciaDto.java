package com.example.api_service.infrastructure.dto;

import com.example.api_service.infrastructure.database.entities.Client;
import com.example.api_service.infrastructure.database.entities.TipoIncidencia;
import com.example.api_service.infrastructure.database.entities.TipoReparacion;

public record IncidenciaDto(Client client, String fecha, String motivo, TipoIncidencia tipoIncidencia,
                            TipoReparacion tipoReparacion, Boolean solucionado) {
}
