package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.UsuarioRequestDTO;
import com.coffeemanager.coffeemanager.dto.UsuarioResponseDTO;
import com.coffeemanager.coffeemanager.dto.ActualizarUsuarioRequestDTO;
import com.coffeemanager.coffeemanager.entity.Usuario;
import com.coffeemanager.coffeemanager.exception.ResourceAlreadyExistsException;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.RolRepository;
import com.coffeemanager.coffeemanager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO guardarUsuario(
            UsuarioRequestDTO request) {

        if (!rolRepository.existsById(request.getIdRol().longValue())) {
            throw new ResourceNotFoundException(
                    "El rol con ID " + request.getIdRol()
                            + " no fue encontrado"
            );
        }

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException(
                    "Ya existe un usuario con el correo: "
                            + request.getEmail()
            );
        }

        Usuario usuario = new Usuario();

        usuario.setIdRol(request.getIdRol());
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setTelefono(request.getTelefono());

        if (request.getEstado() == null || request.getEstado().isBlank()) {
            usuario.setEstado("ACTIVO");
        } else {
            usuario.setEstado(request.getEstado());
        }

        usuario.setFechaCreacion(LocalDateTime.now());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return convertirAResponse(usuarioGuardado);
    }

    public List<UsuarioResponseDTO> listarUsuarios() {

        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    private UsuarioResponseDTO convertirAResponse(Usuario usuario) {

        return new UsuarioResponseDTO(
                usuario.getIdUsuario(),
                usuario.getIdRol(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getEstado(),
                usuario.getFechaCreacion()
        );
    }

    public UsuarioResponseDTO obtenerUsuarioPorId(Integer id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El usuario con ID " + id + " no fue encontrado"
                ));

        return convertirAResponse(usuario);
    }
    public UsuarioResponseDTO actualizarUsuario(
            Integer id,
            ActualizarUsuarioRequestDTO request) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El usuario con ID " + id + " no fue encontrado"
                ));

        if (!rolRepository.existsById(request.getIdRol().longValue())) {
            throw new ResourceNotFoundException(
                    "El rol con ID " + request.getIdRol()
                            + " no fue encontrado"
            );
        }

        if (usuarioRepository.existsByEmailAndIdUsuarioNot(
                request.getEmail(), id)) {

            throw new ResourceAlreadyExistsException(
                    "Ya existe otro usuario con el correo: "
                            + request.getEmail()
            );
        }

        usuario.setIdRol(request.getIdRol());
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setEmail(request.getEmail());
        usuario.setTelefono(request.getTelefono());

        if (request.getEstado() == null || request.getEstado().isBlank()) {
            usuario.setEstado("ACTIVO");
        } else {
            usuario.setEstado(request.getEstado());
        }

        Usuario usuarioActualizado = usuarioRepository.save(usuario);

        return convertirAResponse(usuarioActualizado);
    }

    public void eliminarUsuario(Integer id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El usuario con ID " + id + " no fue encontrado"
                ));

        usuarioRepository.delete(usuario);
    }

    public Usuario obtenerUsuarioPorEmail(String email) {

        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El usuario con correo " + email + " no fue encontrado"
                ));
    }
}