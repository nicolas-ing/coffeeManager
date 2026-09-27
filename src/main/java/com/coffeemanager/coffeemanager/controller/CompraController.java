package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.CompraRequestDTO;
import com.coffeemanager.coffeemanager.dto.CompraResponseDTO;
import com.coffeemanager.coffeemanager.service.CompraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<CompraResponseDTO> crear(
            @Valid @RequestBody CompraRequestDTO request) {

        CompraResponseDTO respuesta =
                compraService.crearCompra(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<CompraResponseDTO>> listar() {

        return ResponseEntity.ok(
                compraService.listarCompras()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraResponseDTO> obtener(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                compraService.obtenerCompra(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraResponseDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody CompraRequestDTO request) {

        return ResponseEntity.ok(
                compraService.actualizarCompra(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {

        compraService.eliminarCompra(id);

        return ResponseEntity.noContent().build();
    }
}