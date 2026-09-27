package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.MovimientoInventarioRequestDTO;
import com.coffeemanager.coffeemanager.dto.MovimientoInventarioResponseDTO;
import com.coffeemanager.coffeemanager.entity.Inventario;
import com.coffeemanager.coffeemanager.entity.MovimientoInventario;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.InventarioRepository;
import com.coffeemanager.coffeemanager.repository.MovimientoInventarioRepository;
import com.coffeemanager.coffeemanager.repository.ProductoRepository;
import com.coffeemanager.coffeemanager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoInventarioService {

    @Autowired
    private MovimientoInventarioRepository movimientoInventarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Transactional
    public MovimientoInventarioResponseDTO registrarMovimiento(
            MovimientoInventarioRequestDTO request) {

        validarProducto(request.getIdProducto());
        validarUsuario(request.getIdUsuario());

        Inventario inventario =
                inventarioRepository.findByIdProducto(
                        request.getIdProducto()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un inventario para el producto con ID "
                                        + request.getIdProducto()
                        )
                );

        String tipoMovimiento =
                request.getTipoMovimiento()
                        .trim()
                        .toUpperCase();

        validarTipoMovimiento(tipoMovimiento);

        if (tipoMovimiento.equals("ENTRADA")) {

            inventario.setCantidadActual(
                    inventario.getCantidadActual()
                            + request.getCantidad()
            );

        } else if (tipoMovimiento.equals("SALIDA")) {

            if (request.getCantidad()
                    > inventario.getCantidadActual()) {

                throw new IllegalArgumentException(
                        "No hay stock suficiente para realizar la salida"
                );
            }

            inventario.setCantidadActual(
                    inventario.getCantidadActual()
                            - request.getCantidad()
            );
        }

        inventario.setFechaActualizacion(
                LocalDateTime.now()
        );

        inventarioRepository.save(inventario);

        MovimientoInventario movimiento =
                new MovimientoInventario();

        movimiento.setIdProducto(request.getIdProducto());
        movimiento.setIdUsuario(request.getIdUsuario());
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setCantidad(request.getCantidad());
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setMotivo(request.getMotivo());
        movimiento.setReferencia(request.getReferencia());

        MovimientoInventario movimientoGuardado =
                movimientoInventarioRepository.save(movimiento);

        return convertirAResponseDTO(movimientoGuardado);
    }

    public List<MovimientoInventarioResponseDTO>
    listarMovimientos() {

        return movimientoInventarioRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public MovimientoInventarioResponseDTO obtenerMovimiento(
            Integer id) {

        MovimientoInventario movimiento =
                movimientoInventarioRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El movimiento con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        return convertirAResponseDTO(movimiento);
    }

    public List<MovimientoInventarioResponseDTO>
    listarPorProducto(Integer idProducto) {

        validarProducto(idProducto);

        return movimientoInventarioRepository
                .findByIdProducto(idProducto)
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public List<MovimientoInventarioResponseDTO>
    listarPorUsuario(Integer idUsuario) {

        validarUsuario(idUsuario);

        return movimientoInventarioRepository
                .findByIdUsuario(idUsuario)
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
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

    private void validarUsuario(Integer idUsuario) {

        if (!usuarioRepository.existsById(idUsuario)) {

            throw new ResourceNotFoundException(
                    "El usuario con ID "
                            + idUsuario
                            + " no fue encontrado"
            );
        }
    }

    private void validarTipoMovimiento(
            String tipoMovimiento) {

        if (!tipoMovimiento.equals("ENTRADA")
                && !tipoMovimiento.equals("SALIDA")) {

            throw new IllegalArgumentException(
                    "El tipo de movimiento debe ser ENTRADA o SALIDA"
            );
        }
    }

    private MovimientoInventarioResponseDTO
    convertirAResponseDTO(
            MovimientoInventario movimiento) {

        return new MovimientoInventarioResponseDTO(
                movimiento.getIdMovimiento(),
                movimiento.getIdProducto(),
                movimiento.getIdUsuario(),
                movimiento.getTipoMovimiento(),
                movimiento.getCantidad(),
                movimiento.getFecha(),
                movimiento.getMotivo(),
                movimiento.getReferencia()
        );
    }
}