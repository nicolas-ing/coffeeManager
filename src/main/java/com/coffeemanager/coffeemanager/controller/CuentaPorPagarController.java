package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.CuentaPorPagarRequestDTO;
import com.coffeemanager.coffeemanager.dto.CuentaPorPagarResponseDTO;
import com.coffeemanager.coffeemanager.service.CuentaPorPagarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas-por-pagar")
public class CuentaPorPagarController {

    @Autowired
    private CuentaPorPagarService cuentaPorPagarService;

    @PostMapping
    public ResponseEntity<CuentaPorPagarResponseDTO> crearCuenta(
            @Valid @RequestBody CuentaPorPagarRequestDTO request) {

        CuentaPorPagarResponseDTO respuesta =
                cuentaPorPagarService.crearCuenta(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<CuentaPorPagarResponseDTO>>
    listarCuentas() {

        List<CuentaPorPagarResponseDTO> respuesta =
                cuentaPorPagarService.listarCuentas();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaPorPagarResponseDTO> obtenerCuenta(
            @PathVariable Integer id) {

        CuentaPorPagarResponseDTO respuesta =
                cuentaPorPagarService.obtenerCuenta(id);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/compra/{idCompra}")
    public ResponseEntity<List<CuentaPorPagarResponseDTO>>
    listarPorCompra(@PathVariable Integer idCompra) {

        List<CuentaPorPagarResponseDTO> respuesta =
                cuentaPorPagarService.listarPorCompra(idCompra);

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaPorPagarResponseDTO> actualizarCuenta(
            @PathVariable Integer id,
            @Valid @RequestBody CuentaPorPagarRequestDTO request) {

        CuentaPorPagarResponseDTO respuesta =
                cuentaPorPagarService.actualizarCuenta(id, request);

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(
            @PathVariable Integer id) {

        cuentaPorPagarService.eliminarCuenta(id);

        return ResponseEntity.noContent().build();
    }
}