package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.EgresoRequestDTO;
import com.coffeemanager.coffeemanager.dto.EgresoResponseDTO;
import com.coffeemanager.coffeemanager.entity.Egreso;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.CompraRepository;
import com.coffeemanager.coffeemanager.repository.EgresoRepository;
import com.coffeemanager.coffeemanager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EgresoService {

    @Autowired
    private EgresoRepository egresoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CompraRepository compraRepository;

    public EgresoResponseDTO crearEgreso(
            EgresoRequestDTO request) {

        validarUsuario(request.getIdUsuario());

        if (request.getIdCompra() != null) {
            validarCompra(request.getIdCompra());
        }

        Egreso egreso = new Egreso();

        egreso.setIdCompra(request.getIdCompra());
        egreso.setIdUsuario(request.getIdUsuario());

        if (request.getFecha() == null) {
            egreso.setFecha(LocalDateTime.now());
        } else {
            egreso.setFecha(request.getFecha());
        }

        egreso.setConcepto(request.getConcepto());
        egreso.setMonto(request.getMonto());
        egreso.setMetodoPago(request.getMetodoPago());
        egreso.setEstado(request.getEstado());

        Egreso egresoGuardado =
                egresoRepository.save(egreso);

        return convertirAResponseDTO(egresoGuardado);
    }

    public List<EgresoResponseDTO> listarEgresos() {

        return egresoRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public EgresoResponseDTO obtenerEgreso(Integer id) {

        Egreso egreso =
                egresoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El egreso con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        return convertirAResponseDTO(egreso);
    }

    public EgresoResponseDTO actualizarEgreso(
            Integer id,
            EgresoRequestDTO request) {

        Egreso egreso =
                egresoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El egreso con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        validarUsuario(request.getIdUsuario());

        if (request.getIdCompra() != null) {
            validarCompra(request.getIdCompra());
        }

        egreso.setIdCompra(request.getIdCompra());
        egreso.setIdUsuario(request.getIdUsuario());

        if (request.getFecha() == null) {
            egreso.setFecha(egreso.getFecha());
        } else {
            egreso.setFecha(request.getFecha());
        }

        egreso.setConcepto(request.getConcepto());
        egreso.setMonto(request.getMonto());
        egreso.setMetodoPago(request.getMetodoPago());
        egreso.setEstado(request.getEstado());

        Egreso egresoActualizado =
                egresoRepository.save(egreso);

        return convertirAResponseDTO(egresoActualizado);
    }

    public void eliminarEgreso(Integer id) {

        Egreso egreso =
                egresoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El egreso con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        egresoRepository.delete(egreso);
    }

    private void validarUsuario(Integer idUsuario) {

        if (!usuarioRepository.existsById(idUsuario)) {

            throw new ResourceNotFoundException(
                    "El usuario con ID "
                            + idUsuario
                            + " no fue encontrado"
            );
        }
    }

    private void validarCompra(Integer idCompra) {

        if (!compraRepository.existsById(idCompra)) {

            throw new ResourceNotFoundException(
                    "La compra con ID "
                            + idCompra
                            + " no fue encontrada"
            );
        }
    }

    private EgresoResponseDTO convertirAResponseDTO(
            Egreso egreso) {

        return new EgresoResponseDTO(
                egreso.getIdEgreso(),
                egreso.getIdCompra(),
                egreso.getIdUsuario(),
                egreso.getFecha(),
                egreso.getConcepto(),
                egreso.getMonto(),
                egreso.getMetodoPago(),
                egreso.getEstado()
        );
    }
}