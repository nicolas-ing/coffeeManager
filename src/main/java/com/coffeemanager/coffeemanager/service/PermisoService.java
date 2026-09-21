package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.PermisoRequestDTO;
import com.coffeemanager.coffeemanager.dto.PermisoResponseDTO;
import com.coffeemanager.coffeemanager.entity.Permiso;
import com.coffeemanager.coffeemanager.exception.ResourceAlreadyExistsException;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.PermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoService {

    @Autowired
    private PermisoRepository permisoRepository;

    public PermisoResponseDTO guardarPermiso(PermisoRequestDTO request) {

        if (permisoRepository.existsByNombre(request.getNombre())) {
            throw new ResourceAlreadyExistsException(
                    "Ya existe un permiso con el nombre: " + request.getNombre()
            );
        }

        Permiso permiso = new Permiso();

        permiso.setNombre(request.getNombre());
        permiso.setDescripcion(request.getDescripcion());
        permiso.setModulo(request.getModulo());

        Permiso permisoGuardado = permisoRepository.save(permiso);

        return new PermisoResponseDTO(
                permisoGuardado.getIdPermiso(),
                permisoGuardado.getNombre(),
                permisoGuardado.getDescripcion(),
                permisoGuardado.getModulo()
        );
    }

    public List<PermisoResponseDTO> listarPermisos() {

        List<Permiso> permisos = permisoRepository.findAll();

        return permisos.stream()
                .map(permiso -> new PermisoResponseDTO(
                        permiso.getIdPermiso(),
                        permiso.getNombre(),
                        permiso.getDescripcion(),
                        permiso.getModulo()
                ))
                .toList();
    }

    public PermisoResponseDTO obtenerPermisoPorId(Long id) {

        Permiso permiso = permisoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El permiso con ID " + id + " no fue encontrado"
                ));

        return new PermisoResponseDTO(
                permiso.getIdPermiso(),
                permiso.getNombre(),
                permiso.getDescripcion(),
                permiso.getModulo()
        );
    }

    public PermisoResponseDTO actualizarPermiso(Long id, PermisoRequestDTO request) {

        Permiso permiso = permisoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El permiso con ID " + id + " no fue encontrado"
                ));

        if (permisoRepository.existsByNombreAndIdPermisoNot(
                request.getNombre(), id)) {

            throw new ResourceAlreadyExistsException(
                    "Ya existe otro permiso con el nombre: "
                            + request.getNombre()
            );
        }

        permiso.setNombre(request.getNombre());
        permiso.setDescripcion(request.getDescripcion());
        permiso.setModulo(request.getModulo());

        Permiso permisoActualizado = permisoRepository.save(permiso);

        return new PermisoResponseDTO(
                permisoActualizado.getIdPermiso(),
                permisoActualizado.getNombre(),
                permisoActualizado.getDescripcion(),
                permisoActualizado.getModulo()
        );
    }
    public void eliminarPermiso(Long id) {

        Permiso permiso = permisoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El permiso con ID " + id + " no fue encontrado"
                ));

        permisoRepository.delete(permiso);
    }
}