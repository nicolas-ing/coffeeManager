package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.RolRequestDTO;
import com.coffeemanager.coffeemanager.dto.RolResponseDTO;
import com.coffeemanager.coffeemanager.service.RolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @PostMapping
    public ResponseEntity<RolResponseDTO> guardarRol(
            @Valid @RequestBody RolRequestDTO request) {

        RolResponseDTO respuesta = rolService.guardarRol(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<RolResponseDTO>> listarRoles() {

        List<RolResponseDTO> roles = rolService.listarRoles();

        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolResponseDTO> obtenerRolPorId(
            @PathVariable Long id) {

        RolResponseDTO respuesta = rolService.obtenerRolPorId(id);

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolResponseDTO> actualizarRol(
            @PathVariable Long id,
            @Valid @RequestBody RolRequestDTO request) {

        RolResponseDTO respuesta = rolService.actualizarRol(id, request);

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(
            @PathVariable Long id) {

        rolService.eliminarRol(id);

        return ResponseEntity.noContent().build();
    }
}