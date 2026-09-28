package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.DetalleVentaRequestDTO;
import com.coffeemanager.coffeemanager.dto.DetalleVentaResponseDTO;
import com.coffeemanager.coffeemanager.service.DetalleVentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-venta")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @PostMapping
    public ResponseEntity<DetalleVentaResponseDTO> crearDetalleVenta(
            @Valid @RequestBody DetalleVentaRequestDTO request) {

        DetalleVentaResponseDTO respuesta =
                detalleVentaService.crearDetalleVenta(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DetalleVentaResponseDTO>> listarDetalles() {

        List<DetalleVentaResponseDTO> respuesta =
                detalleVentaService.listarDetalles();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaResponseDTO> obtenerDetalle(
            @PathVariable Integer id) {

        DetalleVentaResponseDTO respuesta =
                detalleVentaService.obtenerDetalle(id);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<List<DetalleVentaResponseDTO>> listarPorVenta(
            @PathVariable Integer idVenta) {

        List<DetalleVentaResponseDTO> respuesta =
                detalleVentaService.listarPorVenta(idVenta);

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaResponseDTO> actualizarDetalleVenta(
            @PathVariable Integer id,
            @Valid @RequestBody DetalleVentaRequestDTO request) {

        DetalleVentaResponseDTO respuesta =
                detalleVentaService.actualizarDetalleVenta(
                        id,
                        request
                );

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalleVenta(
            @PathVariable Integer id) {

        detalleVentaService.eliminarDetalleVenta(id);

        return ResponseEntity.noContent().build();
    }
}