package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.ClienteRequestDTO;
import com.coffeemanager.coffeemanager.dto.ClienteResponseDTO;
import com.coffeemanager.coffeemanager.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crearCliente(
            @Valid @RequestBody ClienteRequestDTO request) {

        ClienteResponseDTO respuesta =
                clienteService.crearCliente(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {

        List<ClienteResponseDTO> respuesta =
                clienteService.listarClientes();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> obtenerCliente(
            @PathVariable Integer id) {

        ClienteResponseDTO respuesta =
                clienteService.obtenerCliente(id);

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizarCliente(
            @PathVariable Integer id,
            @Valid @RequestBody ClienteRequestDTO request) {

        ClienteResponseDTO respuesta =
                clienteService.actualizarCliente(id, request);

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(
            @PathVariable Integer id) {

        clienteService.eliminarCliente(id);

        return ResponseEntity.noContent().build();
    }
}