package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.ProveedorRequestDTO;
import com.coffeemanager.coffeemanager.dto.ProveedorResponseDTO;
import com.coffeemanager.coffeemanager.entity.Proveedor;
import com.coffeemanager.coffeemanager.exception.ResourceAlreadyExistsException;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public ProveedorResponseDTO crearProveedor(
            ProveedorRequestDTO request) {

        if (proveedorRepository.existsByIdentificacion(
                request.getIdentificacion())) {

            throw new ResourceAlreadyExistsException(
                    "Ya existe un proveedor con la identificación: "
                            + request.getIdentificacion()
            );
        }

        Proveedor proveedor = new Proveedor();

        proveedor.setNombre(request.getNombre());
        proveedor.setIdentificacion(request.getIdentificacion());
        proveedor.setTelefono(request.getTelefono());
        proveedor.setEmail(request.getEmail());
        proveedor.setDireccion(request.getDireccion());

        if (request.getEstado() == null ||
                request.getEstado().isBlank()) {

            proveedor.setEstado("ACTIVO");

        } else {

            proveedor.setEstado(request.getEstado());
        }

        Proveedor proveedorGuardado =
                proveedorRepository.save(proveedor);

        return convertirAResponseDTO(proveedorGuardado);
    }

    public List<ProveedorResponseDTO> listarProveedores() {

        return proveedorRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public ProveedorResponseDTO obtenerProveedor(Integer id) {

        Proveedor proveedor =
                proveedorRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El proveedor con ID " + id
                                                + " no fue encontrado"
                                )
                        );

        return convertirAResponseDTO(proveedor);
    }

    public ProveedorResponseDTO actualizarProveedor(
            Integer id,
            ProveedorRequestDTO request) {

        Proveedor proveedor =
                proveedorRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El proveedor con ID " + id
                                                + " no fue encontrado"
                                )
                        );

        if (proveedorRepository
                .existsByIdentificacionAndIdProveedorNot(
                        request.getIdentificacion(),
                        id)) {

            throw new ResourceAlreadyExistsException(
                    "Ya existe otro proveedor con la identificación: "
                            + request.getIdentificacion()
            );
        }

        proveedor.setNombre(request.getNombre());
        proveedor.setIdentificacion(request.getIdentificacion());
        proveedor.setTelefono(request.getTelefono());
        proveedor.setEmail(request.getEmail());
        proveedor.setDireccion(request.getDireccion());

        if (request.getEstado() == null ||
                request.getEstado().isBlank()) {

            proveedor.setEstado("ACTIVO");

        } else {

            proveedor.setEstado(request.getEstado());
        }

        Proveedor proveedorActualizado =
                proveedorRepository.save(proveedor);

        return convertirAResponseDTO(proveedorActualizado);
    }

    public void eliminarProveedor(Integer id) {

        Proveedor proveedor =
                proveedorRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El proveedor con ID " + id
                                                + " no fue encontrado"
                                )
                        );

        proveedorRepository.delete(proveedor);
    }

    private ProveedorResponseDTO convertirAResponseDTO(
            Proveedor proveedor) {

        return new ProveedorResponseDTO(
                proveedor.getIdProveedor(),
                proveedor.getNombre(),
                proveedor.getIdentificacion(),
                proveedor.getTelefono(),
                proveedor.getEmail(),
                proveedor.getDireccion(),
                proveedor.getEstado()
        );
    }
}