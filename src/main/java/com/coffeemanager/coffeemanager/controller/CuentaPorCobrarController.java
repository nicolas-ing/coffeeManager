package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.CuentaPorCobrarRequestDTO;
import com.coffeemanager.coffeemanager.dto.CuentaPorCobrarResponseDTO;
import com.coffeemanager.coffeemanager.service.CuentaPorCobrarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas-por-cobrar")
public class CuentaPorCobrarController {

    @Autowired
    private CuentaPorCobrarService cuentaPorCobrarService;

    @PostMapping
    public ResponseEntity<CuentaPorCobrarResponseDTO> crearCuenta(
            @Valid @RequestBody CuentaPorCobrarRequestDTO request) {

        CuentaPorCobrarResponseDTO respuesta =
                cuentaPorCobrarService.crearCuenta(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<CuentaPorCobrarResponseDTO>>
    listarCuentas() {

        List<CuentaPorCobrarResponseDTO> respuesta =
                cuentaPorCobrarService.listarCuentas();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaPorCobrarResponseDTO> obtenerCuenta(
            @PathVariable Integer id) {

        CuentaPorCobrarResponseDTO respuesta =
                cuentaPorCobrarService.obtenerCuenta(id);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<CuentaPorCobrarResponseDTO>>
    listarPorCliente(@PathVariable Integer idCliente) {

        List<CuentaPorCobrarResponseDTO> respuesta =
                cuentaPorCobrarService.listarPorCliente(idCliente);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<List<CuentaPorCobrarResponseDTO>>
    listarPorVenta(@PathVariable Integer idVenta) {

        List<CuentaPorCobrarResponseDTO> respuesta =
                cuentaPorCobrarService.listarPorVenta(idVenta);

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaPorCobrarResponseDTO> actualizarCuenta(
            @PathVariable Integer id,
            @Valid @RequestBody CuentaPorCobrarRequestDTO request) {

        CuentaPorCobrarResponseDTO respuesta =
                cuentaPorCobrarService.actualizarCuenta(id, request);

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(
            @PathVariable Integer id) {

        cuentaPorCobrarService.eliminarCuenta(id);

        return ResponseEntity.noContent().build();
    }
}