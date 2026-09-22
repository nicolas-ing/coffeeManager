package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.LoginRequestDTO;
import com.coffeemanager.coffeemanager.dto.LoginResponseDTO;
import com.coffeemanager.coffeemanager.entity.Usuario;
import com.coffeemanager.coffeemanager.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request) {

        Usuario usuario = authService.autenticar(request);

        LoginResponseDTO respuesta = new LoginResponseDTO(
                usuario.getIdUsuario(),
                usuario.getIdRol(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getEstado()
        );

        return ResponseEntity.ok(respuesta);
    }
}