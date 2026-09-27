package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.DetallePedidoRequestDTO;
import com.coffeemanager.coffeemanager.dto.DetallePedidoResponseDTO;
import com.coffeemanager.coffeemanager.entity.DetallePedido;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.DetallePedidoRepository;
import com.coffeemanager.coffeemanager.repository.PedidoRepository;
import com.coffeemanager.coffeemanager.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public DetallePedidoResponseDTO crearDetallePedido(
            DetallePedidoRequestDTO request) {

        validarPedido(request.getIdPedido());
        validarProducto(request.getIdProducto());

        DetallePedido detallePedido = new DetallePedido();

        detallePedido.setIdPedido(request.getIdPedido());
        detallePedido.setIdProducto(request.getIdProducto());
        detallePedido.setCantidad(request.getCantidad());
        detallePedido.setPrecioUnitario(request.getPrecioUnitario());
        detallePedido.setSubtotal(request.getSubtotal());

        DetallePedido detalleGuardado =
                detallePedidoRepository.save(detallePedido);

        return convertirAResponseDTO(detalleGuardado);
    }

    public List<DetallePedidoResponseDTO> listarDetalles() {

        return detallePedidoRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public DetallePedidoResponseDTO obtenerDetalle(
            Integer id) {

        DetallePedido detallePedido =
                detallePedidoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El detalle de pedido con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        return convertirAResponseDTO(detallePedido);
    }

    public List<DetallePedidoResponseDTO> listarPorPedido(
            Integer idPedido) {

        validarPedido(idPedido);

        return detallePedidoRepository
                .findByIdPedido(idPedido)
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public DetallePedidoResponseDTO actualizarDetallePedido(
            Integer id,
            DetallePedidoRequestDTO request) {

        DetallePedido detallePedido =
                detallePedidoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El detalle de pedido con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        validarPedido(request.getIdPedido());
        validarProducto(request.getIdProducto());

        detallePedido.setIdPedido(request.getIdPedido());
        detallePedido.setIdProducto(request.getIdProducto());
        detallePedido.setCantidad(request.getCantidad());
        detallePedido.setPrecioUnitario(request.getPrecioUnitario());
        detallePedido.setSubtotal(request.getSubtotal());

        DetallePedido detalleActualizado =
                detallePedidoRepository.save(detallePedido);

        return convertirAResponseDTO(detalleActualizado);
    }

    public void eliminarDetallePedido(Integer id) {

        DetallePedido detallePedido =
                detallePedidoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El detalle de pedido con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        detallePedidoRepository.delete(detallePedido);
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

    private void validarProducto(Integer idProducto) {

        if (!productoRepository.existsById(idProducto)) {

            throw new ResourceNotFoundException(
                    "El producto con ID "
                            + idProducto
                            + " no fue encontrado"
            );
        }
    }

    private DetallePedidoResponseDTO convertirAResponseDTO(
            DetallePedido detallePedido) {

        return new DetallePedidoResponseDTO(
                detallePedido.getIdDetallePedido(),
                detallePedido.getIdPedido(),
                detallePedido.getIdProducto(),
                detallePedido.getCantidad(),
                detallePedido.getPrecioUnitario(),
                detallePedido.getSubtotal()
        );
    }
}