package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.IngresoRequestDTO;
import com.coffeemanager.coffeemanager.dto.IngresoResponseDTO;
import com.coffeemanager.coffeemanager.service.IngresoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingresos")
public class IngresoController {

    @Autowired
    private IngresoService ingresoService;

    @PostMapping
    public ResponseEntity<IngresoResponseDTO> crearIngreso(
            @Valid @RequestBody IngresoRequestDTO request) {

        IngresoResponseDTO respuesta =
                ingresoService.crearIngreso(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<IngresoResponseDTO>> listarIngresos() {

        List<IngresoResponseDTO> respuesta =
                ingresoService.listarIngresos();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngresoResponseDTO> obtenerIngreso(
            @PathVariable Integer id) {

        IngresoResponseDTO respuesta =
                ingresoService.obtenerIngreso(id);

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngresoResponseDTO> actualizarIngreso(
            @PathVariable Integer id,
            @Valid @RequestBody IngresoRequestDTO request) {

        IngresoResponseDTO respuesta =
                ingresoService.actualizarIngreso(id, request);

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarIngreso(
            @PathVariable Integer id) {

        ingresoService.eliminarIngreso(id);

        return ResponseEntity.noContent().build();
    }
}