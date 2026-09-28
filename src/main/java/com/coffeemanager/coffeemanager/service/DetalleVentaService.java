package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.DetalleVentaRequestDTO;
import com.coffeemanager.coffeemanager.dto.DetalleVentaResponseDTO;
import com.coffeemanager.coffeemanager.entity.DetalleVenta;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.DetalleVentaRepository;
import com.coffeemanager.coffeemanager.repository.ProductoRepository;
import com.coffeemanager.coffeemanager.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public DetalleVentaResponseDTO crearDetalleVenta(
            DetalleVentaRequestDTO request) {

        validarVenta(request.getIdVenta());
        validarProducto(request.getIdProducto());

        DetalleVenta detalleVenta = new DetalleVenta();

        detalleVenta.setIdVenta(request.getIdVenta());
        detalleVenta.setIdProducto(request.getIdProducto());
        detalleVenta.setCantidad(request.getCantidad());
        detalleVenta.setPrecioUnitario(request.getPrecioUnitario());
        detalleVenta.setSubtotal(request.getSubtotal());

        DetalleVenta detalleGuardado =
                detalleVentaRepository.save(detalleVenta);

        return convertirAResponseDTO(detalleGuardado);
    }

    public List<DetalleVentaResponseDTO> listarDetalles() {

        return detalleVentaRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public DetalleVentaResponseDTO obtenerDetalle(Integer id) {

        DetalleVenta detalleVenta =
                detalleVentaRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El detalle de venta con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        return convertirAResponseDTO(detalleVenta);
    }

    public List<DetalleVentaResponseDTO> listarPorVenta(
            Integer idVenta) {

        validarVenta(idVenta);

        return detalleVentaRepository
                .findByIdVenta(idVenta)
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public DetalleVentaResponseDTO actualizarDetalleVenta(
            Integer id,
            DetalleVentaRequestDTO request) {

        DetalleVenta detalleVenta =
                detalleVentaRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El detalle de venta con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        validarVenta(request.getIdVenta());
        validarProducto(request.getIdProducto());

        detalleVenta.setIdVenta(request.getIdVenta());
        detalleVenta.setIdProducto(request.getIdProducto());
        detalleVenta.setCantidad(request.getCantidad());
        detalleVenta.setPrecioUnitario(request.getPrecioUnitario());
        detalleVenta.setSubtotal(request.getSubtotal());

        DetalleVenta detalleActualizado =
                detalleVentaRepository.save(detalleVenta);

        return convertirAResponseDTO(detalleActualizado);
    }

    public void eliminarDetalleVenta(Integer id) {

        DetalleVenta detalleVenta =
                detalleVentaRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El detalle de venta con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        detalleVentaRepository.delete(detalleVenta);
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

    private void validarProducto(Integer idProducto) {

        if (!productoRepository.existsById(idProducto)) {

            throw new ResourceNotFoundException(
                    "El producto con ID "
                            + idProducto
                            + " no fue encontrado"
            );
        }
    }

    private DetalleVentaResponseDTO convertirAResponseDTO(
            DetalleVenta detalleVenta) {

        return new DetalleVentaResponseDTO(
                detalleVenta.getIdDetalleVenta(),
                detalleVenta.getIdVenta(),
                detalleVenta.getIdProducto(),
                detalleVenta.getCantidad(),
                detalleVenta.getPrecioUnitario(),
                detalleVenta.getSubtotal()
        );
    }
}