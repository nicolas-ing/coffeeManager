package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.CategoriaProductoRequestDTO;
import com.coffeemanager.coffeemanager.dto.CategoriaProductoResponseDTO;
import com.coffeemanager.coffeemanager.entity.CategoriaProducto;
import com.coffeemanager.coffeemanager.exception.ResourceAlreadyExistsException;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.CategoriaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaProductoService {

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    public CategoriaProductoResponseDTO crearCategoria(
            CategoriaProductoRequestDTO request) {

        if (categoriaProductoRepository.existsByNombre(request.getNombre())) {
            throw new ResourceAlreadyExistsException(
                    "Ya existe una categoría con el nombre: "
                            + request.getNombre()
            );
        }

        CategoriaProducto categoria = new CategoriaProducto();

        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());

        CategoriaProducto categoriaGuardada =
                categoriaProductoRepository.save(categoria);

        return convertirAResponseDTO(categoriaGuardada);
    }

    public List<CategoriaProductoResponseDTO> listarCategorias() {

        return categoriaProductoRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public CategoriaProductoResponseDTO obtenerCategoria(
            Integer id) {

        CategoriaProducto categoria =
                categoriaProductoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "La categoría con ID "
                                                + id
                                                + " no fue encontrada"
                                )
                        );

        return convertirAResponseDTO(categoria);
    }

    public CategoriaProductoResponseDTO actualizarCategoria(
            Integer id,
            CategoriaProductoRequestDTO request) {

        CategoriaProducto categoria =
                categoriaProductoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "La categoría con ID "
                                                + id
                                                + " no fue encontrada"
                                )
                        );

        if (categoriaProductoRepository
                .existsByNombreAndIdCategoriaNot(
                        request.getNombre(),
                        id)) {

            throw new ResourceAlreadyExistsException(
                    "Ya existe otra categoría con el nombre: "
                            + request.getNombre()
            );
        }

        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());

        CategoriaProducto categoriaActualizada =
                categoriaProductoRepository.save(categoria);

        return convertirAResponseDTO(categoriaActualizada);
    }

    public void eliminarCategoria(Integer id) {

        CategoriaProducto categoria =
                categoriaProductoRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "La categoría con ID "
                                                + id
                                                + " no fue encontrada"
                                )
                        );

        categoriaProductoRepository.delete(categoria);
    }

    private CategoriaProductoResponseDTO convertirAResponseDTO(
            CategoriaProducto categoria) {

        return new CategoriaProductoResponseDTO(
                categoria.getIdCategoria(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }
}