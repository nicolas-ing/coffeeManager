package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.PedidoRequestDTO;
import com.coffeemanager.coffeemanager.dto.PedidoResponseDTO;
import com.coffeemanager.coffeemanager.entity.Pedido;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.ClienteRepository;
import com.coffeemanager.coffeemanager.repository.PedidoRepository;
import com.coffeemanager.coffeemanager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public PedidoResponseDTO crearPedido(
            PedidoRequestDTO request) {

        validarCliente(request.getIdCliente());
        validarUsuario(request.getIdUsuario());

        Pedido pedido = new Pedido();

        pedido.setIdCliente(request.getIdCliente());
        pedido.setIdUsuario(request.getIdUsuario());

        if (request.getFecha() == null) {
            pedido.setFecha(LocalDateTime.now());
        } else {
            pedido.setFecha(request.getFecha());
        }

        pedido.setEstado(request.getEstado());
        pedido.setObservaciones(request.getObservaciones());
        pedido.setSubtotal(request.getSubtotal());
        pedido.setImpuesto(request.getImpuesto());
        pedido.setTotal(request.getTotal());

        Pedido pedidoGuardado =
                pedidoRepository.save(pedido);

        return convertirAResponseDTO(pedidoGuardado);
    }

    public List<PedidoResponseDTO> listarPedidos() {

        return pedidoRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public PedidoResponseDTO obtenerPedido(Integer id) {

        Pedido pedido =
                pedidoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El pedido con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        return convertirAResponseDTO(pedido);
    }

    public PedidoResponseDTO actualizarPedido(
            Integer id,
            PedidoRequestDTO request) {

        Pedido pedido =
                pedidoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El pedido con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        validarCliente(request.getIdCliente());
        validarUsuario(request.getIdUsuario());

        pedido.setIdCliente(request.getIdCliente());
        pedido.setIdUsuario(request.getIdUsuario());

        if (request.getFecha() == null) {
            pedido.setFecha(pedido.getFecha());
        } else {
            pedido.setFecha(request.getFecha());
        }

        pedido.setEstado(request.getEstado());
        pedido.setObservaciones(request.getObservaciones());
        pedido.setSubtotal(request.getSubtotal());
        pedido.setImpuesto(request.getImpuesto());
        pedido.setTotal(request.getTotal());

        Pedido pedidoActualizado =
                pedidoRepository.save(pedido);

        return convertirAResponseDTO(pedidoActualizado);
    }

    public void eliminarPedido(Integer id) {

        Pedido pedido =
                pedidoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El pedido con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        pedidoRepository.delete(pedido);
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

    private PedidoResponseDTO convertirAResponseDTO(
            Pedido pedido) {

        return new PedidoResponseDTO(
                pedido.getIdPedido(),
                pedido.getIdCliente(),
                pedido.getIdUsuario(),
                pedido.getFecha(),
                pedido.getEstado(),
                pedido.getObservaciones(),
                pedido.getSubtotal(),
                pedido.getImpuesto(),
                pedido.getTotal()
        );
    }
}