package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.PermisoRequestDTO;
import com.coffeemanager.coffeemanager.dto.PermisoResponseDTO;
import com.coffeemanager.coffeemanager.service.PermisoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permisos")
public class PermisoController {

    @Autowired
    private PermisoService permisoService;

    @PostMapping
    public ResponseEntity<PermisoResponseDTO> guardarPermiso(
            @Valid @RequestBody PermisoRequestDTO request) {

        PermisoResponseDTO respuesta = permisoService.guardarPermiso(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<PermisoResponseDTO>> listarPermisos() {

        List<PermisoResponseDTO> permisos = permisoService.listarPermisos();

        return ResponseEntity.ok(permisos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermisoResponseDTO> obtenerPermisoPorId(
            @PathVariable Long id) {

        PermisoResponseDTO respuesta = permisoService.obtenerPermisoPorId(id);

        return ResponseEntity.ok(respuesta);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PermisoResponseDTO> actualizarPermiso(
            @PathVariable Long id,
            @Valid @RequestBody PermisoRequestDTO request) {

        PermisoResponseDTO respuesta =
                permisoService.actualizarPermiso(id, request);

        return ResponseEntity.ok(respuesta);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPermiso(
            @PathVariable Long id) {

        permisoService.eliminarPermiso(id);

        return ResponseEntity.noContent().build();
    }
}