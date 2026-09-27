package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.PedidoRequestDTO;
import com.coffeemanager.coffeemanager.dto.PedidoResponseDTO;
import com.coffeemanager.coffeemanager.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> crearPedido(
            @Valid @RequestBody PedidoRequestDTO request) {

        PedidoResponseDTO respuesta =
                pedidoService.crearPedido(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos() {

        List<PedidoResponseDTO> respuesta =
                pedidoService.listarPedidos();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> obtenerPedido(
            @PathVariable Integer id) {

        PedidoResponseDTO respuesta =
                pedidoService.obtenerPedido(id);

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> actualizarPedido(
            @PathVariable Integer id,
            @Valid @RequestBody PedidoRequestDTO request) {

        PedidoResponseDTO respuesta =
                pedidoService.actualizarPedido(id, request);

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(
            @PathVariable Integer id) {

        pedidoService.eliminarPedido(id);

        return ResponseEntity.noContent().build();
    }
}