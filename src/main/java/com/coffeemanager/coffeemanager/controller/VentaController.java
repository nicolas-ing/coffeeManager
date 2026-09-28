package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.VentaRequestDTO;
import com.coffeemanager.coffeemanager.dto.VentaResponseDTO;
import com.coffeemanager.coffeemanager.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaResponseDTO> crearVenta(
            @Valid @RequestBody VentaRequestDTO request) {

        VentaResponseDTO respuesta =
                ventaService.crearVenta(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> listarVentas() {

        List<VentaResponseDTO> respuesta =
                ventaService.listarVentas();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> obtenerVenta(
            @PathVariable Integer id) {

        VentaResponseDTO respuesta =
                ventaService.obtenerVenta(id);

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> actualizarVenta(
            @PathVariable Integer id,
            @Valid @RequestBody VentaRequestDTO request) {

        VentaResponseDTO respuesta =
                ventaService.actualizarVenta(id, request);

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(
            @PathVariable Integer id) {

        ventaService.eliminarVenta(id);

        return ResponseEntity.noContent().build();
    }
}