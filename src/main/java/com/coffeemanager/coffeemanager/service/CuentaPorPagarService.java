package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.CuentaPorPagarRequestDTO;
import com.coffeemanager.coffeemanager.dto.CuentaPorPagarResponseDTO;
import com.coffeemanager.coffeemanager.entity.CuentaPorPagar;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.CompraRepository;
import com.coffeemanager.coffeemanager.repository.CuentaPorPagarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaPorPagarService {

    @Autowired
    private CuentaPorPagarRepository cuentaPorPagarRepository;

    @Autowired
    private CompraRepository compraRepository;

    public CuentaPorPagarResponseDTO crearCuenta(
            CuentaPorPagarRequestDTO request) {

        validarCompra(request.getIdCompra());

        CuentaPorPagar cuenta = new CuentaPorPagar();

        cuenta.setIdCompra(request.getIdCompra());
        cuenta.setFechaEmision(request.getFechaEmision());
        cuenta.setFechaVencimiento(request.getFechaVencimiento());
        cuenta.setMonto(request.getMonto());
        cuenta.setSaldo(request.getSaldo());
        cuenta.setEstado(request.getEstado());

        CuentaPorPagar guardada =
                cuentaPorPagarRepository.save(cuenta);

        return convertirAResponseDTO(guardada);
    }

    public List<CuentaPorPagarResponseDTO> listarCuentas() {

        return cuentaPorPagarRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public CuentaPorPagarResponseDTO obtenerCuenta(Integer id) {

        CuentaPorPagar cuenta =
                cuentaPorPagarRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "La cuenta por pagar con ID "
                                                + id
                                                + " no fue encontrada"));

        return convertirAResponseDTO(cuenta);
    }

    public List<CuentaPorPagarResponseDTO> listarPorCompra(
            Integer idCompra) {

        validarCompra(idCompra);

        return cuentaPorPagarRepository.findByIdCompra(idCompra)
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public CuentaPorPagarResponseDTO actualizarCuenta(
            Integer id,
            CuentaPorPagarRequestDTO request) {

        CuentaPorPagar cuenta =
                cuentaPorPagarRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "La cuenta por pagar con ID "
                                                + id
                                                + " no fue encontrada"));

        validarCompra(request.getIdCompra());

        cuenta.setIdCompra(request.getIdCompra());
        cuenta.setFechaEmision(request.getFechaEmision());
        cuenta.setFechaVencimiento(request.getFechaVencimiento());
        cuenta.setMonto(request.getMonto());
        cuenta.setSaldo(request.getSaldo());
        cuenta.setEstado(request.getEstado());

        CuentaPorPagar actualizada =
                cuentaPorPagarRepository.save(cuenta);

        return convertirAResponseDTO(actualizada);
    }

    public void eliminarCuenta(Integer id) {

        CuentaPorPagar cuenta =
                cuentaPorPagarRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "La cuenta por pagar con ID "
                                                + id
                                                + " no fue encontrada"));

        cuentaPorPagarRepository.delete(cuenta);
    }

    private void validarCompra(Integer idCompra) {

        if (!compraRepository.existsById(idCompra)) {
            throw new ResourceNotFoundException(
                    "La compra con ID "
                            + idCompra
                            + " no fue encontrada");
        }
    }

    private CuentaPorPagarResponseDTO convertirAResponseDTO(
            CuentaPorPagar cuenta) {

        return new CuentaPorPagarResponseDTO(
                cuenta.getIdCuentaPagar(),
                cuenta.getIdCompra(),
                cuenta.getFechaEmision(),
                cuenta.getFechaVencimiento(),
                cuenta.getMonto(),
                cuenta.getSaldo(),
                cuenta.getEstado()
        );
    }
}