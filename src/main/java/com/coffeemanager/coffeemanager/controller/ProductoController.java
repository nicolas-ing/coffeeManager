package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.ProductoRequestDTO;
import com.coffeemanager.coffeemanager.dto.ProductoResponseDTO;
import com.coffeemanager.coffeemanager.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crear(
            @Valid @RequestBody ProductoRequestDTO request) {

        ProductoResponseDTO respuesta =
                productoService.crearProducto(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listar() {

        return ResponseEntity.ok(
                productoService.listarProductos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtener(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                productoService.obtenerProducto(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ProductoRequestDTO request) {

        return ResponseEntity.ok(
                productoService.actualizarProducto(
                        id,
                        request
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {

        productoService.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }
}