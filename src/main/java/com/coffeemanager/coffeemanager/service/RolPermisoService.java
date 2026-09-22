package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.RolPermisoRequestDTO;
import com.coffeemanager.coffeemanager.dto.RolPermisoResponseDTO;
import com.coffeemanager.coffeemanager.entity.RolPermiso;
import com.coffeemanager.coffeemanager.exception.ResourceAlreadyExistsException;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.PermisoRepository;
import com.coffeemanager.coffeemanager.repository.RolPermisoRepository;
import com.coffeemanager.coffeemanager.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RolPermisoService {

    @Autowired
    private RolPermisoRepository rolPermisoRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PermisoRepository permisoRepository;

    public RolPermisoResponseDTO asignarPermiso(
            RolPermisoRequestDTO request) {

        if (!rolRepository.existsById(request.getIdRol().longValue())) {
            throw new ResourceNotFoundException(
                    "El rol con ID " + request.getIdRol() + " no fue encontrado"
            );
        }

        if (!permisoRepository.existsById(request.getIdPermiso().longValue())) {
            throw new ResourceNotFoundException(
                    "El permiso con ID " + request.getIdPermiso() + " no fue encontrado"
            );
        }

        if (rolPermisoRepository.existsByIdRolAndIdPermiso(
                request.getIdRol(),
                request.getIdPermiso())) {

            throw new ResourceAlreadyExistsException(
                    "El permiso ya está asignado al rol"
            );
        }

        RolPermiso rolPermiso = new RolPermiso();

        rolPermiso.setIdRol(request.getIdRol());
        rolPermiso.setIdPermiso(request.getIdPermiso());
        rolPermiso.setFechaAsignacion(LocalDateTime.now());

        RolPermiso rolPermisoGuardado =
                rolPermisoRepository.save(rolPermiso);

        return new RolPermisoResponseDTO(
                rolPermisoGuardado.getIdRolPermiso(),
                rolPermisoGuardado.getIdRol(),
                rolPermisoGuardado.getIdPermiso(),
                rolPermisoGuardado.getFechaAsignacion()
        );
    }

    public List<RolPermisoResponseDTO> listarAsignaciones() {

        return rolPermisoRepository.findAll()
                .stream()
                .map(rolPermiso -> new RolPermisoResponseDTO(
                        rolPermiso.getIdRolPermiso(),
                        rolPermiso.getIdRol(),
                        rolPermiso.getIdPermiso(),
                        rolPermiso.getFechaAsignacion()
                ))
                .toList();
    }

    public void eliminarAsignacion(Integer id) {

        RolPermiso rolPermiso = rolPermisoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "La asignación con ID " + id + " no fue encontrada"
                ));

        rolPermisoRepository.delete(rolPermiso);
    }
}