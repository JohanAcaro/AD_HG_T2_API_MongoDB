package com.example.api_service;

import com.example.api_service.infrastructure.database.entities.Client;
import com.example.api_service.infrastructure.database.entities.TipoIncidencia;
import com.example.api_service.infrastructure.database.entities.TipoReparacion;
import com.example.api_service.infrastructure.dto.IncidenciaDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@SpringBootApplication
public class ApiServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiServiceApplication.class, args);

        var sc = new Scanner(System.in);
        var opcion = "";
        do {
            System.out.println("Bienvenido a la aplicación de WATERMELON");
            System.out.println("¿Qué tipo de usuario eres?");
            System.out.println("1. Cliente");
            System.out.println("2. Dirección");
            System.out.println("3. Técnico");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextLine();

            switch (opcion){
                case "1" ->
                    // Lógica para registrar un nuevo cliente
                        nuevoCliente();
                case "2" ->
                    // Lógica para direccion
                        direccion();
                case "3" ->
                    // Lógica para tecnico
                        tecnico();
                case "4" ->
                        System.out.println("Gracias por usar el sistema de atención al cliente de WATERMELON");
                default -> System.out.println("Opción inválida, por favor seleccione una opción válida");

            }

        }while (!opcion.equals("4"));
    }

    private static void tecnico() {
        var sc = new Scanner(System.in);
        String usuario= "tecnico";
        String contrasena= "1234";
        System.out.println("Login de Técnico");
        System.out.println("Introduce tu nombre de usuario");
        String usuarioInt = sc.nextLine();
        System.out.println("Introduce tu contraseña");
        String contrasenaInt = sc.nextLine();

        if (usuarioInt.equals(usuario) && contrasenaInt.equals(contrasena)) {
            System.out.println("Bienvenido técnico de WATERMELON");


        } else {
            System.out.println("Usuario o contraseña incorrectos");
        }

    }

    private static void direccion() {
        var sc = new Scanner(System.in);
        String usuario= "direccion";
        String contrasena= "1234";
        System.out.println("Login de Dirección");
        System.out.println("Introduce tu nombre de usuario");
        String usuarioInt = sc.nextLine();
        System.out.println("Introduce tu contraseña");
        String contrasenaInt = sc.nextLine();

        if (usuarioInt.equals(usuario) && contrasenaInt.equals(contrasena)) {
            System.out.println("Bienvenido a la dirección de WATERMELON");
            //Muestra las incidencias totales


        } else {
            System.out.println("Usuario o contraseña incorrectos");
        }
    }

    private static void nuevoCliente() {
        var sc = new Scanner(System.in);
        System.out.println("Bievenido al sistema de atención al cliente de WATERMELON");
        System.out.println("¿Cómo te llamas?");
        String nombre = sc.nextLine();
        System.out.println("¿Cuáles son tus apellidos?");
        String apellidos = sc.nextLine();
        System.out.println("¿Cuál es tu número de teléfono?");
        String telefono = sc.nextLine();
        System.out.println("¿Cuál es el modelo de tu teléfono?");
        String modelo = sc.nextLine();

        Client cliente = new Client(nombre, apellidos, telefono, modelo);

        // Convertir a string la fecha actual
        LocalDate date = LocalDate.now();
        //cambio de formato de fecha
        date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String fecha = date.toString();

        System.out.println("¿Cuál es el motivo de tu llamada?");
        String motivo = sc.nextLine();
        System.out.println("¿Qué tipo de incidencia es?");
        System.out.println("¿Hardware o Software?");
        TipoIncidencia tipoIncidencia = TipoIncidencia.valueOf(sc.nextLine().toUpperCase());
        System.out.println("¿Tipo de reparación?");
        System.out.println("¿Física o Virtual?");
        TipoReparacion tipoReparacion = TipoReparacion.valueOf(sc.nextLine().toUpperCase());
        System.out.println("¿Hemos podido solucionar tu problema?");
        System.out.println("¿S o N?");
        String respuesta = sc.nextLine().toUpperCase();
        boolean solucionado;
        solucionado = respuesta.equals("S");

        RestTemplate restTemplate = new RestTemplate();
        IncidenciaDto incidenciaDto = new IncidenciaDto(cliente, fecha, motivo, tipoIncidencia, tipoReparacion, solucionado);
        // Establecer los datos de IncidenciaDto aquí
        String url = "http://localhost:8080/insert";
        String response = restTemplate.postForObject(url, incidenciaDto, String.class);
        System.out.println(response);
    }

}
