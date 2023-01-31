package com.example.api_service.infrastructure.database.entities;

import lombok.Data;

@Data
public class Client {
    String nombre;
    String apellidos;
    String tlf;
    String modeloTlf;
}
