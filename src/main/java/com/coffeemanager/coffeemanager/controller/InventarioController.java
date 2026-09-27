package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.InventarioRequestDTO;
import com.coffeemanager.coffeemanager.dto.InventarioResponseDTO;
import com.coffeemanager.coffeemanager.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<InventarioResponseDTO> crear(
            @Valid @RequestBody InventarioRequestDTO request) {

        InventarioResponseDTO respuesta =
                inventarioService.crearInventario(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<InventarioResponseDTO>> listar() {

        return ResponseEntity.ok(
                inventarioService.listarInventarios()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> obtener(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                inventarioService.obtenerInventario(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody InventarioRequestDTO request) {

        return ResponseEntity.ok(
                inventarioService.actualizarInventario(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {

        inventarioService.eliminarInventario(id);

        return ResponseEntity.noContent().build();
    }
}