package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.MovimientoInventarioRequestDTO;
import com.coffeemanager.coffeemanager.dto.MovimientoInventarioResponseDTO;
import com.coffeemanager.coffeemanager.service.MovimientoInventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos-inventario")
public class MovimientoInventarioController {

    @Autowired
    private MovimientoInventarioService movimientoInventarioService;

    @PostMapping
    public ResponseEntity<MovimientoInventarioResponseDTO>
    registrarMovimiento(
            @Valid @RequestBody MovimientoInventarioRequestDTO request) {

        MovimientoInventarioResponseDTO respuesta =
                movimientoInventarioService.registrarMovimiento(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<MovimientoInventarioResponseDTO>>
    listarMovimientos() {

        List<MovimientoInventarioResponseDTO> respuesta =
                movimientoInventarioService.listarMovimientos();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventarioResponseDTO>
    obtenerMovimiento(@PathVariable Integer id) {

        MovimientoInventarioResponseDTO respuesta =
                movimientoInventarioService.obtenerMovimiento(id);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<MovimientoInventarioResponseDTO>>
    listarPorProducto(@PathVariable Integer idProducto) {

        List<MovimientoInventarioResponseDTO> respuesta =
                movimientoInventarioService.listarPorProducto(idProducto);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<MovimientoInventarioResponseDTO>>
    listarPorUsuario(@PathVariable Integer idUsuario) {

        List<MovimientoInventarioResponseDTO> respuesta =
                movimientoInventarioService.listarPorUsuario(idUsuario);

        return ResponseEntity.ok(respuesta);
    }
}