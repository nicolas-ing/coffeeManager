package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaProductoRepository
        extends JpaRepository<CategoriaProducto, Integer> {

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdCategoriaNot(
            String nombre,
            Integer idCategoria
    );
}