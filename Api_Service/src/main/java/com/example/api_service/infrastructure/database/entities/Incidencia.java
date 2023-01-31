package com.example.api_service.infrastructure.database.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "incidencia")
@Data
public class Incidencia {
    @Id
    String id;
    Client client;
    String fecha;
    String motivo;
    TipoIncidencia tipoIncidencia;
    TipoReparacion tipoReparacion;
    Boolean solucionado;
}
