package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.CategoriaProductoRequestDTO;
import com.coffeemanager.coffeemanager.dto.CategoriaProductoResponseDTO;
import com.coffeemanager.coffeemanager.service.CategoriaProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias-producto")
public class CategoriaProductoController {

    @Autowired
    private CategoriaProductoService categoriaProductoService;

    @PostMapping
    public ResponseEntity<CategoriaProductoResponseDTO> crear(
            @Valid @RequestBody CategoriaProductoRequestDTO request) {

        CategoriaProductoResponseDTO respuesta =
                categoriaProductoService.crearCategoria(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProductoResponseDTO>> listar() {

        return ResponseEntity.ok(
                categoriaProductoService.listarCategorias()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProductoResponseDTO> obtener(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                categoriaProductoService.obtenerCategoria(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProductoResponseDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody CategoriaProductoRequestDTO request) {

        return ResponseEntity.ok(
                categoriaProductoService.actualizarCategoria(
                        id,
                        request
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {

        categoriaProductoService.eliminarCategoria(id);

        return ResponseEntity.noContent().build();
    }
}