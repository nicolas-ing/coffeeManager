package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.DetallePedidoRequestDTO;
import com.coffeemanager.coffeemanager.dto.DetallePedidoResponseDTO;
import com.coffeemanager.coffeemanager.service.DetallePedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @PostMapping
    public ResponseEntity<DetallePedidoResponseDTO>
    crearDetallePedido(
            @Valid @RequestBody DetallePedidoRequestDTO request) {

        DetallePedidoResponseDTO respuesta =
                detallePedidoService.crearDetallePedido(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<DetallePedidoResponseDTO>>
    listarDetalles() {

        List<DetallePedidoResponseDTO> respuesta =
                detallePedidoService.listarDetalles();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoResponseDTO>
    obtenerDetalle(@PathVariable Integer id) {

        DetallePedidoResponseDTO respuesta =
                detallePedidoService.obtenerDetalle(id);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<List<DetallePedidoResponseDTO>>
    listarPorPedido(@PathVariable Integer idPedido) {

        List<DetallePedidoResponseDTO> respuesta =
                detallePedidoService.listarPorPedido(idPedido);

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedidoResponseDTO>
    actualizarDetallePedido(
            @PathVariable Integer id,
            @Valid @RequestBody DetallePedidoRequestDTO request) {

        DetallePedidoResponseDTO respuesta =
                detallePedidoService.actualizarDetallePedido(
                        id,
                        request
                );

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetallePedido(
            @PathVariable Integer id) {

        detallePedidoService.eliminarDetallePedido(id);

        return ResponseEntity.noContent().build();
    }
}