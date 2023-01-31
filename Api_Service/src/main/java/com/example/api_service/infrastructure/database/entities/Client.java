package com.example.api_service.infrastructure.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    String nombre;
    String apellidos;
    String tlf;
    String modeloTlf;
}
