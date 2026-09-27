package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.DetalleCompraRequestDTO;
import com.coffeemanager.coffeemanager.dto.DetalleCompraResponseDTO;
import com.coffeemanager.coffeemanager.service.DetalleCompraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-compra")
public class DetalleCompraController {

    @Autowired
    private DetalleCompraService detalleCompraService;

    // CREAR
    @PostMapping
    public ResponseEntity<DetalleCompraResponseDTO> crear(
            @Valid @RequestBody DetalleCompraRequestDTO request) {

        DetalleCompraResponseDTO respuesta =
                detalleCompraService.crearDetalle(request);

        return ResponseEntity.ok(respuesta);
    }

    // LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<DetalleCompraResponseDTO>> listar() {

        return ResponseEntity.ok(
                detalleCompraService.listarDetalles()
        );
    }

    // OBTENER POR ID
    @GetMapping("/{id}")
    public ResponseEntity<DetalleCompraResponseDTO> obtener(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                detalleCompraService.obtenerDetalle(id)
        );
    }

    // LISTAR POR COMPRA
    @GetMapping("/compra/{idCompra}")
    public ResponseEntity<List<DetalleCompraResponseDTO>> listarPorCompra(
            @PathVariable Integer idCompra) {

        return ResponseEntity.ok(
                detalleCompraService.listarPorCompra(idCompra)
        );
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<DetalleCompraResponseDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody DetalleCompraRequestDTO request) {

        return ResponseEntity.ok(
                detalleCompraService.actualizarDetalle(id, request)
        );
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {

        detalleCompraService.eliminarDetalle(id);

        return ResponseEntity.noContent().build();
    }
}