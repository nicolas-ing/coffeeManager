package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.ProductoRequestDTO;
import com.coffeemanager.coffeemanager.dto.ProductoResponseDTO;
import com.coffeemanager.coffeemanager.entity.Producto;
import com.coffeemanager.coffeemanager.exception.ResourceAlreadyExistsException;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.CategoriaProductoRepository;
import com.coffeemanager.coffeemanager.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    public ProductoResponseDTO crearProducto(
            ProductoRequestDTO request) {

        if (!categoriaProductoRepository
                .existsById(request.getIdCategoria())) {

            throw new ResourceNotFoundException(
                    "La categoría con ID "
                            + request.getIdCategoria()
                            + " no fue encontrada"
            );
        }

        if (productoRepository.existsByCodigo(request.getCodigo())) {

            throw new ResourceAlreadyExistsException(
                    "Ya existe un producto con el código: "
                            + request.getCodigo()
            );
        }

        Producto producto = new Producto();

        producto.setIdCategoria(request.getIdCategoria());
        producto.setCodigo(request.getCodigo());
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setTipo(request.getTipo());
        producto.setUnidadMedida(request.getUnidadMedida());
        producto.setPrecioCompra(request.getPrecioCompra());
        producto.setPrecioVenta(request.getPrecioVenta());

        if (request.getEstado() == null ||
                request.getEstado().isBlank()) {

            producto.setEstado("ACTIVO");

        } else {

            producto.setEstado(request.getEstado());
        }

        Producto productoGuardado =
                productoRepository.save(producto);

        return convertirAResponseDTO(productoGuardado);
    }

    public List<ProductoResponseDTO> listarProductos() {

        return productoRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public ProductoResponseDTO obtenerProducto(Integer id) {

        Producto producto =
                productoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El producto con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        return convertirAResponseDTO(producto);
    }

    public ProductoResponseDTO actualizarProducto(
            Integer id,
            ProductoRequestDTO request) {

        Producto producto =
                productoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El producto con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        if (!categoriaProductoRepository
                .existsById(request.getIdCategoria())) {

            throw new ResourceNotFoundException(
                    "La categoría con ID "
                            + request.getIdCategoria()
                            + " no fue encontrada"
            );
        }

        if (productoRepository
                .existsByCodigoAndIdProductoNot(
                        request.getCodigo(),
                        id)) {

            throw new ResourceAlreadyExistsException(
                    "Ya existe otro producto con el código: "
                            + request.getCodigo()
            );
        }

        producto.setIdCategoria(request.getIdCategoria());
        producto.setCodigo(request.getCodigo());
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setTipo(request.getTipo());
        producto.setUnidadMedida(request.getUnidadMedida());
        producto.setPrecioCompra(request.getPrecioCompra());
        producto.setPrecioVenta(request.getPrecioVenta());

        if (request.getEstado() == null ||
                request.getEstado().isBlank()) {

            producto.setEstado("ACTIVO");

        } else {

            producto.setEstado(request.getEstado());
        }

        Producto productoActualizado =
                productoRepository.save(producto);

        return convertirAResponseDTO(productoActualizado);
    }

    public void eliminarProducto(Integer id) {

        Producto producto =
                productoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El producto con ID "
                                                + id
                                                + " no fue encontrado"
                                )
                        );

        productoRepository.delete(producto);
    }

    private ProductoResponseDTO convertirAResponseDTO(
            Producto producto) {

        return new ProductoResponseDTO(
                producto.getIdProducto(),
                producto.getIdCategoria(),
                producto.getCodigo(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getTipo(),
                producto.getUnidadMedida(),
                producto.getPrecioCompra(),
                producto.getPrecioVenta(),
                producto.getEstado()
        );
    }
}