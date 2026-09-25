package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.LoginRequestDTO;
import com.coffeemanager.coffeemanager.dto.LoginResponseDTO;
import com.coffeemanager.coffeemanager.entity.Usuario;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.UsuarioRepository;
import com.coffeemanager.coffeemanager.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;



    public LoginResponseDTO autenticar(LoginRequestDTO request) {

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Credenciales inválidas"
                ));

        if (!passwordEncoder.matches(
                request.getPassword(),
                usuario.getPassword())) {

            throw new ResourceNotFoundException(
                    "Credenciales inválidas"
            );
        }

        String token = jwtService.generarToken(usuario);

        return new LoginResponseDTO(
                token,
                usuario.getIdUsuario(),
                usuario.getIdRol(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getEstado()
        );
    }
}