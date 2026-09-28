package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.CuentaPorCobrarRequestDTO;
import com.coffeemanager.coffeemanager.dto.CuentaPorCobrarResponseDTO;
import com.coffeemanager.coffeemanager.entity.CuentaPorCobrar;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.ClienteRepository;
import com.coffeemanager.coffeemanager.repository.CuentaPorCobrarRepository;
import com.coffeemanager.coffeemanager.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaPorCobrarService {

    @Autowired
    private CuentaPorCobrarRepository cuentaPorCobrarRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public CuentaPorCobrarResponseDTO crearCuenta(
            CuentaPorCobrarRequestDTO request) {

        validarVenta(request.getIdVenta());
        validarCliente(request.getIdCliente());

        CuentaPorCobrar cuenta = new CuentaPorCobrar();

        cuenta.setIdVenta(request.getIdVenta());
        cuenta.setIdCliente(request.getIdCliente());
        cuenta.setFechaEmision(request.getFechaEmision());
        cuenta.setFechaVencimiento(request.getFechaVencimiento());
        cuenta.setMonto(request.getMonto());
        cuenta.setSaldo(request.getSaldo());
        cuenta.setEstado(request.getEstado());

        CuentaPorCobrar guardada =
                cuentaPorCobrarRepository.save(cuenta);

        return convertirAResponseDTO(guardada);
    }

    public List<CuentaPorCobrarResponseDTO> listarCuentas() {

        return cuentaPorCobrarRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public CuentaPorCobrarResponseDTO obtenerCuenta(Integer id) {

        CuentaPorCobrar cuenta =
                cuentaPorCobrarRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "La cuenta por cobrar con ID "
                                                + id
                                                + " no fue encontrada"));

        return convertirAResponseDTO(cuenta);
    }

    public List<CuentaPorCobrarResponseDTO> listarPorCliente(
            Integer idCliente) {

        validarCliente(idCliente);

        return cuentaPorCobrarRepository.findByIdCliente(idCliente)
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public List<CuentaPorCobrarResponseDTO> listarPorVenta(
            Integer idVenta) {

        validarVenta(idVenta);

        return cuentaPorCobrarRepository.findByIdVenta(idVenta)
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public CuentaPorCobrarResponseDTO actualizarCuenta(
            Integer id,
            CuentaPorCobrarRequestDTO request) {

        CuentaPorCobrar cuenta =
                cuentaPorCobrarRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "La cuenta por cobrar con ID "
                                                + id
                                                + " no fue encontrada"));

        validarVenta(request.getIdVenta());
        validarCliente(request.getIdCliente());

        cuenta.setIdVenta(request.getIdVenta());
        cuenta.setIdCliente(request.getIdCliente());
        cuenta.setFechaEmision(request.getFechaEmision());
        cuenta.setFechaVencimiento(request.getFechaVencimiento());
        cuenta.setMonto(request.getMonto());
        cuenta.setSaldo(request.getSaldo());
        cuenta.setEstado(request.getEstado());

        CuentaPorCobrar actualizada =
                cuentaPorCobrarRepository.save(cuenta);

        return convertirAResponseDTO(actualizada);
    }

    public void eliminarCuenta(Integer id) {

        CuentaPorCobrar cuenta =
                cuentaPorCobrarRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "La cuenta por cobrar con ID "
                                                + id
                                                + " no fue encontrada"));

        cuentaPorCobrarRepository.delete(cuenta);
    }

    private void validarVenta(Integer idVenta) {

        if (!ventaRepository.existsById(idVenta)) {
            throw new ResourceNotFoundException(
                    "La venta con ID "
                            + idVenta
                            + " no fue encontrada");
        }
    }

    private void validarCliente(Integer idCliente) {

        if (!clienteRepository.existsById(idCliente)) {
            throw new ResourceNotFoundException(
                    "El cliente con ID "
                            + idCliente
                            + " no fue encontrado");
        }
    }

    private CuentaPorCobrarResponseDTO convertirAResponseDTO(
            CuentaPorCobrar cuenta) {

        return new CuentaPorCobrarResponseDTO(
                cuenta.getIdCuentaCobrar(),
                cuenta.getIdVenta(),
                cuenta.getIdCliente(),
                cuenta.getFechaEmision(),
                cuenta.getFechaVencimiento(),
                cuenta.getMonto(),
                cuenta.getSaldo(),
                cuenta.getEstado()
        );
    }
}