package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.RolRequestDTO;
import com.coffeemanager.coffeemanager.dto.RolResponseDTO;
import com.coffeemanager.coffeemanager.entity.Rol;
import com.coffeemanager.coffeemanager.exception.ResourceAlreadyExistsException;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public RolResponseDTO guardarRol(RolRequestDTO request) {

        if (rolRepository.existsByNombre(request.getNombre())) {
            throw new ResourceAlreadyExistsException(
                    "Ya existe un rol con el nombre: " + request.getNombre()
            );
        }
        Rol rol = new Rol();

        rol.setNombre(request.getNombre());
        rol.setDescripcion(request.getDescripcion());

        Rol rolGuardado = rolRepository.save(rol);

        return new RolResponseDTO(
                rolGuardado.getIdRol(),
                rolGuardado.getNombre(),
                rolGuardado.getDescripcion()
        );
    }

    public List<RolResponseDTO> listarRoles() {

        List<Rol> roles = rolRepository.findAll();

        return roles.stream()
                .map(rol -> new RolResponseDTO(
                        rol.getIdRol(),
                        rol.getNombre(),
                        rol.getDescripcion()
                ))
                .toList();
    }
    public RolResponseDTO obtenerRolPorId(Long id) {

        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El rol con ID " + id + " no fue encontrado"
                ));

        return new RolResponseDTO(
                rol.getIdRol(),
                rol.getNombre(),
                rol.getDescripcion()
        );
    }

    public RolResponseDTO actualizarRol(Long id, RolRequestDTO request) {

        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El rol con ID " + id + " no fue encontrado"
                ));

        if (rolRepository.existsByNombreAndIdRolNot(request.getNombre(), id)) {
            throw new ResourceAlreadyExistsException(
                    "Ya existe otro rol con el nombre: " + request.getNombre()
            );
        }

        rol.setNombre(request.getNombre());
        rol.setDescripcion(request.getDescripcion());

        Rol rolActualizado = rolRepository.save(rol);

        return new RolResponseDTO(
                rolActualizado.getIdRol(),
                rolActualizado.getNombre(),
                rolActualizado.getDescripcion()
        );
    }

    public void eliminarRol(Long id) {

        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El rol con ID " + id + " no fue encontrado"
                ));

        rolRepository.delete(rol);
    }

    public boolean existePorNombre(String nombre) {
        return rolRepository.existsByNombre(nombre);
    }
}