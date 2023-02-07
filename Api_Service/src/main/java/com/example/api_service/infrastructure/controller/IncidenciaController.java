package com.example.api_service.infrastructure.controller;

import com.example.api_service.infrastructure.database.entities.Incidencia;
import com.example.api_service.infrastructure.database.entities.TipoIncidencia;
import com.example.api_service.infrastructure.database.entities.TipoReparacion;
import com.example.api_service.infrastructure.database.repository.IncidenciaRepository;
import com.example.api_service.infrastructure.dto.IncidenciaConverter;
import com.example.api_service.infrastructure.dto.IncidenciaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

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

    // Info para la dirección de la empresa
    @GetMapping("/list-llamadas")
    public ResponseEntity<String> listLlamadas() {
        var total = incidenciaRepository.count();
        List<Incidencia> incidenciaList = incidenciaRepository.findAll();
        AtomicInteger softwareCalls = new AtomicInteger(), hardwareCalls = new AtomicInteger(), solution = new AtomicInteger(), taller = new AtomicInteger();

        incidenciaList.stream().filter(inc -> inc.getTipoIncidencia().equals(TipoIncidencia.SOFTWARE)).forEach(inc -> softwareCalls.getAndIncrement());
        incidenciaList.stream().filter(inc -> inc.getTipoIncidencia().equals(TipoIncidencia.HARDWARE)).forEach(inc -> hardwareCalls.getAndIncrement());
        incidenciaList.stream().filter(Incidencia::getSolucionado).forEach(inc -> solution.getAndIncrement());
        incidenciaList.stream().filter(inc -> inc.getTipoReparacion().equals(TipoReparacion.FISICA)).forEach(inc -> taller.getAndIncrement());

        return ResponseEntity.ok(
                "Total de llamadas: " + total + "\n" +
                        "Llamadas de software: " + softwareCalls.get() + "\n" +
                        "Llamadas de hardware: " + hardwareCalls.get() + "\n" +
                        "Llamadas solucionadas: " + solution.get() + "\n" +
                        "Solucionadas en taller: " + taller.get()
        );
    }

    @GetMapping("/list-llamadas/{fecha}")
    public ResponseEntity<String> listByDate(@PathVariable("fecha") String fecha){
        List<Incidencia> incidenciaList = incidenciaRepository.findByFecha(fecha);
        return ResponseEntity.ok(
                "Lista de llamadas en "+fecha+" :\n"+ incidenciaList
        );
    }

    // Info para técnicos de atención al cliente
    @GetMapping("/info-cliente/{idLlamada}")
    public ResponseEntity<String> infoCliente(@PathVariable("idLlamada") String idLlamada) {
        Optional<Incidencia> incidenciaById = incidenciaRepository.findById(idLlamada);
        if (incidenciaById.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Incidencia incidencia = incidenciaById.get();
            return ResponseEntity.ok(
                    "Cliente: " + incidencia.getClient() + "\n" +
                            "Historial de llamadas: " + incidenciaRepository.findByClient(incidencia.getClient())
            );
        }
    }

    // Método Update incidencia
    @PutMapping("/update/{idLlamada}")
    public ResponseEntity<String> updateIncidencia(@PathVariable("idLlamada") String idLlamada, @RequestBody IncidenciaDto incidenciaDto) {
        if (incidenciaRepository.existsById(idLlamada)) {
            var incidencia = IncidenciaConverter
                    .convertFrom(incidenciaDto)
                    .build();
            incidencia.setId(idLlamada);
            var rs = incidenciaRepository.save(incidencia);
            return ResponseEntity.ok("Incidencia actualizada con id: " + rs.getId());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método Delete incidencia
    @DeleteMapping("/delete/{idLlamada}")
    public ResponseEntity<String> deleteIncidencia(@PathVariable("idLlamada") String idLlamada) {
        if (incidenciaRepository.existsById(idLlamada)) {
            incidenciaRepository.deleteById(idLlamada);
            return ResponseEntity.ok("Incidencia eliminada con id: " + idLlamada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
