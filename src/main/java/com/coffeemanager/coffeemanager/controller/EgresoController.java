package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.EgresoRequestDTO;
import com.coffeemanager.coffeemanager.dto.EgresoResponseDTO;
import com.coffeemanager.coffeemanager.service.EgresoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/egresos")
public class EgresoController {

    @Autowired
    private EgresoService egresoService;

    @PostMapping
    public ResponseEntity<EgresoResponseDTO> crearEgreso(
            @Valid @RequestBody EgresoRequestDTO request) {

        EgresoResponseDTO respuesta =
                egresoService.crearEgreso(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<EgresoResponseDTO>> listarEgresos() {

        List<EgresoResponseDTO> respuesta =
                egresoService.listarEgresos();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EgresoResponseDTO> obtenerEgreso(
            @PathVariable Integer id) {

        EgresoResponseDTO respuesta =
                egresoService.obtenerEgreso(id);

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EgresoResponseDTO> actualizarEgreso(
            @PathVariable Integer id,
            @Valid @RequestBody EgresoRequestDTO request) {

        EgresoResponseDTO respuesta =
                egresoService.actualizarEgreso(id, request);

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEgreso(
            @PathVariable Integer id) {

        egresoService.eliminarEgreso(id);

        return ResponseEntity.noContent().build();
    }
}