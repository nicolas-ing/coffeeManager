package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.ProveedorRequestDTO;
import com.coffeemanager.coffeemanager.dto.ProveedorResponseDTO;
import com.coffeemanager.coffeemanager.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping
    public ResponseEntity<ProveedorResponseDTO> crear(
            @Valid @RequestBody ProveedorRequestDTO request) {

        ProveedorResponseDTO respuesta =
                proveedorService.crearProveedor(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<ProveedorResponseDTO>> listar() {

        return ResponseEntity.ok(
                proveedorService.listarProveedores()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> obtener(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                proveedorService.obtenerProveedor(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ProveedorRequestDTO request) {

        return ResponseEntity.ok(
                proveedorService.actualizarProveedor(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {

        proveedorService.eliminarProveedor(id);

        return ResponseEntity.noContent().build();
    }
}