package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.DetalleCompraRequestDTO;
import com.coffeemanager.coffeemanager.dto.DetalleCompraResponseDTO;
import com.coffeemanager.coffeemanager.entity.DetalleCompra;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.CompraRepository;
import com.coffeemanager.coffeemanager.repository.DetalleCompraRepository;
import com.coffeemanager.coffeemanager.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleCompraService {

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // CREAR
    public DetalleCompraResponseDTO crearDetalle(
            DetalleCompraRequestDTO request) {

        validarCompra(request.getIdCompra());
        validarProducto(request.getIdProducto());

        DetalleCompra detalle = new DetalleCompra();

        detalle.setIdCompra(request.getIdCompra());
        detalle.setIdProducto(request.getIdProducto());
        detalle.setCantidad(request.getCantidad());
        detalle.setPrecioUnitario(request.getPrecioUnitario());
        detalle.setSubtotal(request.getSubtotal());

        DetalleCompra detalleGuardado =
                detalleCompraRepository.save(detalle);

        return convertirAResponseDTO(detalleGuardado);
    }

    // LISTAR TODOS
    public List<DetalleCompraResponseDTO> listarDetalles() {

        return detalleCompraRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    // OBTENER POR ID
    public DetalleCompraResponseDTO obtenerDetalle(Integer id) {

        DetalleCompra detalle =
                detalleCompraRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El detalle de compra con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        return convertirAResponseDTO(detalle);
    }

    // LISTAR POR COMPRA
    public List<DetalleCompraResponseDTO> listarPorCompra(
            Integer idCompra) {

        validarCompra(idCompra);

        return detalleCompraRepository.findByIdCompra(idCompra)
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    // ACTUALIZAR
    public DetalleCompraResponseDTO actualizarDetalle(
            Integer id,
            DetalleCompraRequestDTO request) {

        DetalleCompra detalle =
                detalleCompraRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El detalle de compra con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        validarCompra(request.getIdCompra());
        validarProducto(request.getIdProducto());

        detalle.setIdCompra(request.getIdCompra());
        detalle.setIdProducto(request.getIdProducto());
        detalle.setCantidad(request.getCantidad());
        detalle.setPrecioUnitario(request.getPrecioUnitario());
        detalle.setSubtotal(request.getSubtotal());

        DetalleCompra detalleActualizado =
                detalleCompraRepository.save(detalle);

        return convertirAResponseDTO(detalleActualizado);
    }

    // ELIMINAR
    public void eliminarDetalle(Integer id) {

        DetalleCompra detalle =
                detalleCompraRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El detalle de compra con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        detalleCompraRepository.delete(detalle);
    }

    // VALIDAR COMPRA
    private void validarCompra(Integer idCompra) {

        if (!compraRepository.existsById(idCompra)) {

            throw new ResourceNotFoundException(
                    "La compra con ID "
                            + idCompra
                            + " no fue encontrada"
            );
        }
    }

    // VALIDAR PRODUCTO
    private void validarProducto(Integer idProducto) {

        if (!productoRepository.existsById(idProducto)) {

            throw new ResourceNotFoundException(
                    "El producto con ID "
                            + idProducto
                            + " no fue encontrado"
            );
        }
    }

    // CONVERTIR ENTITY → RESPONSE DTO
    private DetalleCompraResponseDTO convertirAResponseDTO(
            DetalleCompra detalle) {

        return new DetalleCompraResponseDTO(
                detalle.getIdDetalleCompra(),
                detalle.getIdCompra(),
                detalle.getIdProducto(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario(),
                detalle.getSubtotal()
        );
    }
}