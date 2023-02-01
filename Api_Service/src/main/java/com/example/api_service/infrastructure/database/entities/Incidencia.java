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

    public Incidencia(Client client, String fecha, String motivo, TipoIncidencia tipoIncidencia, TipoReparacion tipoReparacion, Boolean solucionado) {
        this.client = client;
        this.fecha = fecha;
        this.motivo = motivo;
        this.tipoIncidencia = tipoIncidencia;
        this.tipoReparacion = tipoReparacion;
        this.solucionado = solucionado;
    }
}
