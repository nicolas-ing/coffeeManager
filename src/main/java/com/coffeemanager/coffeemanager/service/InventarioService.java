package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.InventarioRequestDTO;
import com.coffeemanager.coffeemanager.dto.InventarioResponseDTO;
import com.coffeemanager.coffeemanager.entity.Inventario;
import com.coffeemanager.coffeemanager.exception.ResourceAlreadyExistsException;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.InventarioRepository;
import com.coffeemanager.coffeemanager.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public InventarioResponseDTO crearInventario(
            InventarioRequestDTO request) {

        validarProducto(request.getIdProducto());

        if (inventarioRepository.existsByIdProducto(
                request.getIdProducto())) {

            throw new ResourceAlreadyExistsException(
                    "Ya existe un inventario para el producto con ID "
                            + request.getIdProducto()
            );
        }

        validarRangoStock(
                request.getStockMinimo(),
                request.getStockMaximo()
        );

        Inventario inventario = new Inventario();

        inventario.setIdProducto(request.getIdProducto());
        inventario.setCantidadActual(request.getCantidadActual());
        inventario.setStockMinimo(request.getStockMinimo());
        inventario.setStockMaximo(request.getStockMaximo());
        inventario.setFechaActualizacion(LocalDateTime.now());

        Inventario inventarioGuardado =
                inventarioRepository.save(inventario);

        return convertirAResponseDTO(inventarioGuardado);
    }

    public List<InventarioResponseDTO> listarInventarios() {

        return inventarioRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public InventarioResponseDTO obtenerInventario(Integer id) {

        Inventario inventario =
                inventarioRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El inventario con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        return convertirAResponseDTO(inventario);
    }

    public InventarioResponseDTO actualizarInventario(
            Integer id,
            InventarioRequestDTO request) {

        Inventario inventario =
                inventarioRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El inventario con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        validarProducto(request.getIdProducto());

        if (inventarioRepository
                .existsByIdProductoAndIdInventarioNot(
                        request.getIdProducto(),
                        id)) {

            throw new ResourceAlreadyExistsException(
                    "Ya existe un inventario para el producto con ID "
                            + request.getIdProducto()
            );
        }

        validarRangoStock(
                request.getStockMinimo(),
                request.getStockMaximo()
        );

        inventario.setIdProducto(request.getIdProducto());
        inventario.setCantidadActual(request.getCantidadActual());
        inventario.setStockMinimo(request.getStockMinimo());
        inventario.setStockMaximo(request.getStockMaximo());
        inventario.setFechaActualizacion(LocalDateTime.now());

        Inventario inventarioActualizado =
                inventarioRepository.save(inventario);

        return convertirAResponseDTO(inventarioActualizado);
    }

    public void eliminarInventario(Integer id) {

        Inventario inventario =
                inventarioRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El inventario con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        inventarioRepository.delete(inventario);
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

    private void validarRangoStock(
            Integer stockMinimo,
            Integer stockMaximo) {

        if (stockMaximo < stockMinimo) {

            throw new IllegalArgumentException(
                    "El stock máximo debe ser mayor o igual al stock mínimo"
            );
        }
    }

    private InventarioResponseDTO convertirAResponseDTO(
            Inventario inventario) {

        return new InventarioResponseDTO(
                inventario.getIdInventario(),
                inventario.getIdProducto(),
                inventario.getCantidadActual(),
                inventario.getStockMinimo(),
                inventario.getStockMaximo(),
                inventario.getFechaActualizacion()
        );
    }
}