package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.RolPermisoRequestDTO;
import com.coffeemanager.coffeemanager.dto.RolPermisoResponseDTO;
import com.coffeemanager.coffeemanager.service.RolPermisoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles-permisos")
public class RolPermisoController {

    @Autowired
    private RolPermisoService rolPermisoService;

    @PostMapping
    public ResponseEntity<RolPermisoResponseDTO> asignarPermiso(
            @Valid @RequestBody RolPermisoRequestDTO request) {

        RolPermisoResponseDTO respuesta =
                rolPermisoService.asignarPermiso(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<RolPermisoResponseDTO>> listarAsignaciones() {

        List<RolPermisoResponseDTO> asignaciones =
                rolPermisoService.listarAsignaciones();

        return ResponseEntity.ok(asignaciones);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsignacion(
            @PathVariable Integer id) {

        rolPermisoService.eliminarAsignacion(id);

        return ResponseEntity.noContent().build();
    }
}