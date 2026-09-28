package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.VentaRequestDTO;
import com.coffeemanager.coffeemanager.dto.VentaResponseDTO;
import com.coffeemanager.coffeemanager.entity.Venta;
import com.coffeemanager.coffeemanager.exception.ResourceAlreadyExistsException;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.ClienteRepository;
import com.coffeemanager.coffeemanager.repository.PedidoRepository;
import com.coffeemanager.coffeemanager.repository.UsuarioRepository;
import com.coffeemanager.coffeemanager.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public VentaResponseDTO crearVenta(VentaRequestDTO request) {

        validarCliente(request.getIdCliente());
        validarUsuario(request.getIdUsuario());

        if (request.getIdPedido() != null) {
            validarPedido(request.getIdPedido());
        }

        if (ventaRepository.existsByNumeroDocumento(
                request.getNumeroDocumento())) {

            throw new ResourceAlreadyExistsException(
                    "Ya existe una venta con el número de documento "
                            + request.getNumeroDocumento()
            );
        }

        Venta venta = new Venta();

        venta.setIdCliente(request.getIdCliente());
        venta.setIdUsuario(request.getIdUsuario());
        venta.setIdPedido(request.getIdPedido());

        if (request.getFecha() == null) {
            venta.setFecha(LocalDateTime.now());
        } else {
            venta.setFecha(request.getFecha());
        }

        venta.setNumeroDocumento(request.getNumeroDocumento());
        venta.setTipoPago(request.getTipoPago());
        venta.setEstado(request.getEstado());
        venta.setSubtotal(request.getSubtotal());
        venta.setImpuesto(request.getImpuesto());
        venta.setTotal(request.getTotal());

        Venta ventaGuardada = ventaRepository.save(venta);

        return convertirAResponseDTO(ventaGuardada);
    }

    public List<VentaResponseDTO> listarVentas() {

        return ventaRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public VentaResponseDTO obtenerVenta(Integer id) {

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "La venta con ID "
                                        + id
                                        + " no fue encontrada"
                        )
                );

        return convertirAResponseDTO(venta);
    }

    public VentaResponseDTO actualizarVenta(
            Integer id,
            VentaRequestDTO request) {

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "La venta con ID "
                                        + id
                                        + " no fue encontrada"
                        )
                );

        validarCliente(request.getIdCliente());
        validarUsuario(request.getIdUsuario());

        if (request.getIdPedido() != null) {
            validarPedido(request.getIdPedido());
        }

        if (ventaRepository
                .existsByNumeroDocumentoAndIdVentaNot(
                        request.getNumeroDocumento(),
                        id)) {

            throw new ResourceAlreadyExistsException(
                    "Ya existe una venta con el número de documento "
                            + request.getNumeroDocumento()
            );
        }

        venta.setIdCliente(request.getIdCliente());
        venta.setIdUsuario(request.getIdUsuario());
        venta.setIdPedido(request.getIdPedido());

        if (request.getFecha() == null) {
            venta.setFecha(venta.getFecha());
        } else {
            venta.setFecha(request.getFecha());
        }

        venta.setNumeroDocumento(request.getNumeroDocumento());
        venta.setTipoPago(request.getTipoPago());
        venta.setEstado(request.getEstado());
        venta.setSubtotal(request.getSubtotal());
        venta.setImpuesto(request.getImpuesto());
        venta.setTotal(request.getTotal());

        Venta ventaActualizada = ventaRepository.save(venta);

        return convertirAResponseDTO(ventaActualizada);
    }

    public void eliminarVenta(Integer id) {

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "La venta con ID "
                                        + id
                                        + " no fue encontrada"
                        )
                );

        ventaRepository.delete(venta);
    }

    private void validarCliente(Integer idCliente) {

        if (!clienteRepository.existsById(idCliente)) {

            throw new ResourceNotFoundException(
                    "El cliente con ID "
                            + idCliente
                            + " no fue encontrado"
            );
        }
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

    private void validarPedido(Integer idPedido) {

        if (!pedidoRepository.existsById(idPedido)) {

            throw new ResourceNotFoundException(
                    "El pedido con ID "
                            + idPedido
                            + " no fue encontrado"
            );
        }
    }

    private VentaResponseDTO convertirAResponseDTO(Venta venta) {

        return new VentaResponseDTO(
                venta.getIdVenta(),
                venta.getIdCliente(),
                venta.getIdUsuario(),
                venta.getIdPedido(),
                venta.getFecha(),
                venta.getNumeroDocumento(),
                venta.getTipoPago(),
                venta.getEstado(),
                venta.getSubtotal(),
                venta.getImpuesto(),
                venta.getTotal()
        );
    }
}