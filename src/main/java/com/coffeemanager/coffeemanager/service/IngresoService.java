package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.IngresoRequestDTO;
import com.coffeemanager.coffeemanager.dto.IngresoResponseDTO;
import com.coffeemanager.coffeemanager.entity.Ingreso;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.IngresoRepository;
import com.coffeemanager.coffeemanager.repository.UsuarioRepository;
import com.coffeemanager.coffeemanager.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IngresoService {

    @Autowired
    private IngresoRepository ingresoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VentaRepository ventaRepository;

    public IngresoResponseDTO crearIngreso(
            IngresoRequestDTO request) {

        validarUsuario(request.getIdUsuario());

        if (request.getIdVenta() != null) {
            validarVenta(request.getIdVenta());
        }

        Ingreso ingreso = new Ingreso();

        ingreso.setIdVenta(request.getIdVenta());
        ingreso.setIdUsuario(request.getIdUsuario());

        if (request.getFecha() == null) {
            ingreso.setFecha(LocalDateTime.now());
        } else {
            ingreso.setFecha(request.getFecha());
        }

        ingreso.setConcepto(request.getConcepto());
        ingreso.setMonto(request.getMonto());
        ingreso.setMetodoPago(request.getMetodoPago());
        ingreso.setEstado(request.getEstado());

        Ingreso ingresoGuardado =
                ingresoRepository.save(ingreso);

        return convertirAResponseDTO(ingresoGuardado);
    }

    public List<IngresoResponseDTO> listarIngresos() {

        return ingresoRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public IngresoResponseDTO obtenerIngreso(Integer id) {

        Ingreso ingreso =
                ingresoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El ingreso con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        return convertirAResponseDTO(ingreso);
    }

    public IngresoResponseDTO actualizarIngreso(
            Integer id,
            IngresoRequestDTO request) {

        Ingreso ingreso =
                ingresoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El ingreso con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        validarUsuario(request.getIdUsuario());

        if (request.getIdVenta() != null) {
            validarVenta(request.getIdVenta());
        }

        ingreso.setIdVenta(request.getIdVenta());
        ingreso.setIdUsuario(request.getIdUsuario());

        if (request.getFecha() == null) {
            ingreso.setFecha(ingreso.getFecha());
        } else {
            ingreso.setFecha(request.getFecha());
        }

        ingreso.setConcepto(request.getConcepto());
        ingreso.setMonto(request.getMonto());
        ingreso.setMetodoPago(request.getMetodoPago());
        ingreso.setEstado(request.getEstado());

        Ingreso ingresoActualizado =
                ingresoRepository.save(ingreso);

        return convertirAResponseDTO(ingresoActualizado);
    }

    public void eliminarIngreso(Integer id) {

        Ingreso ingreso =
                ingresoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El ingreso con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        ingresoRepository.delete(ingreso);
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

    private void validarVenta(Integer idVenta) {

        if (!ventaRepository.existsById(idVenta)) {

            throw new ResourceNotFoundException(
                    "La venta con ID "
                            + idVenta
                            + " no fue encontrada"
            );
        }
    }

    private IngresoResponseDTO convertirAResponseDTO(
            Ingreso ingreso) {

        return new IngresoResponseDTO(
                ingreso.getIdIngreso(),
                ingreso.getIdVenta(),
                ingreso.getIdUsuario(),
                ingreso.getFecha(),
                ingreso.getConcepto(),
                ingreso.getMonto(),
                ingreso.getMetodoPago(),
                ingreso.getEstado()
        );
    }
}