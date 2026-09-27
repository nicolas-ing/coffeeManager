package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.CompraRequestDTO;
import com.coffeemanager.coffeemanager.dto.CompraResponseDTO;
import com.coffeemanager.coffeemanager.entity.Compra;
import com.coffeemanager.coffeemanager.exception.ResourceAlreadyExistsException;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.CompraRepository;
import com.coffeemanager.coffeemanager.repository.ProveedorRepository;
import com.coffeemanager.coffeemanager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // CREAR
    public CompraResponseDTO crearCompra(CompraRequestDTO request) {

        validarProveedor(request.getIdProveedor());
        validarUsuario(request.getIdUsuario());
        validarNumeroDocumento(request.getNumeroDocumento());

        Compra compra = new Compra();

        compra.setIdProveedor(request.getIdProveedor());
        compra.setIdUsuario(request.getIdUsuario());

        if (request.getFecha() == null) {
            compra.setFecha(LocalDateTime.now());
        } else {
            compra.setFecha(request.getFecha());
        }

        compra.setNumeroDocumento(request.getNumeroDocumento());

        if (request.getEstado() == null ||
                request.getEstado().isBlank()) {

            compra.setEstado("REGISTRADA");

        } else {
            compra.setEstado(request.getEstado());
        }

        compra.setSubtotal(request.getSubtotal());
        compra.setImpuesto(request.getImpuesto());
        compra.setTotal(request.getTotal());

        Compra compraGuardada = compraRepository.save(compra);

        return convertirAResponseDTO(compraGuardada);
    }

    // LISTAR
    public List<CompraResponseDTO> listarCompras() {

        return compraRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    // OBTENER POR ID
    public CompraResponseDTO obtenerCompra(Integer id) {

        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "La compra con ID " + id + " no fue encontrada"
                ));

        return convertirAResponseDTO(compra);
    }

    // ACTUALIZAR
    public CompraResponseDTO actualizarCompra(
            Integer id,
            CompraRequestDTO request) {

        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "La compra con ID " + id + " no fue encontrada"
                ));

        validarProveedor(request.getIdProveedor());
        validarUsuario(request.getIdUsuario());

        if (compraRepository.existsByNumeroDocumentoAndIdCompraNot(
                request.getNumeroDocumento(),
                id)) {

            throw new ResourceAlreadyExistsException(
                    "Ya existe otra compra con el número de documento: "
                            + request.getNumeroDocumento()
            );
        }

        compra.setIdProveedor(request.getIdProveedor());
        compra.setIdUsuario(request.getIdUsuario());

        if (request.getFecha() == null) {
            compra.setFecha(compra.getFecha());
        } else {
            compra.setFecha(request.getFecha());
        }

        compra.setNumeroDocumento(request.getNumeroDocumento());

        if (request.getEstado() == null ||
                request.getEstado().isBlank()) {

            compra.setEstado("REGISTRADA");

        } else {
            compra.setEstado(request.getEstado());
        }

        compra.setSubtotal(request.getSubtotal());
        compra.setImpuesto(request.getImpuesto());
        compra.setTotal(request.getTotal());

        Compra compraActualizada = compraRepository.save(compra);

        return convertirAResponseDTO(compraActualizada);
    }

    // ELIMINAR
    public void eliminarCompra(Integer id) {

        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "La compra con ID " + id + " no fue encontrada"
                ));

        compraRepository.delete(compra);
    }

    // VALIDAR PROVEEDOR
    private void validarProveedor(Integer idProveedor) {

        if (!proveedorRepository.existsById(idProveedor)) {

            throw new ResourceNotFoundException(
                    "El proveedor con ID "
                            + idProveedor
                            + " no fue encontrado"
            );
        }
    }

    // VALIDAR USUARIO
    private void validarUsuario(Integer idUsuario) {

        if (!usuarioRepository.existsById(idUsuario)) {

            throw new ResourceNotFoundException(
                    "El usuario con ID "
                            + idUsuario
                            + " no fue encontrado"
            );
        }
    }

    // VALIDAR NÚMERO DE DOCUMENTO
    private void validarNumeroDocumento(String numeroDocumento) {

        if (compraRepository.existsByNumeroDocumento(numeroDocumento)) {

            throw new ResourceAlreadyExistsException(
                    "Ya existe una compra con el número de documento: "
                            + numeroDocumento
            );
        }
    }

    // CONVERTIR ENTITY → RESPONSE DTO
    private CompraResponseDTO convertirAResponseDTO(Compra compra) {

        return new CompraResponseDTO(
                compra.getIdCompra(),
                compra.getIdProveedor(),
                compra.getIdUsuario(),
                compra.getFecha(),
                compra.getNumeroDocumento(),
                compra.getEstado(),
                compra.getSubtotal(),
                compra.getImpuesto(),
                compra.getTotal()
        );
    }
}