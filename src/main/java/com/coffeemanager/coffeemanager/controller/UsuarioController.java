package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.ActualizarUsuarioRequestDTO;
import com.coffeemanager.coffeemanager.dto.UsuarioRequestDTO;
import com.coffeemanager.coffeemanager.dto.UsuarioResponseDTO;
import com.coffeemanager.coffeemanager.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> guardarUsuario(
            @Valid @RequestBody UsuarioRequestDTO request) {

        UsuarioResponseDTO respuesta =
                usuarioService.guardarUsuario(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {

        List<UsuarioResponseDTO> usuarios =
                usuarioService.listarUsuarios();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorId(
            @PathVariable Integer id) {

        UsuarioResponseDTO respuesta =
                usuarioService.obtenerUsuarioPorId(id);

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(
            @PathVariable Integer id,
            @Valid @RequestBody ActualizarUsuarioRequestDTO request) {

        UsuarioResponseDTO respuesta =
                usuarioService.actualizarUsuario(id, request);

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(
            @PathVariable Integer id) {

        usuarioService.eliminarUsuario(id);

        return ResponseEntity.noContent().build();
    }
}