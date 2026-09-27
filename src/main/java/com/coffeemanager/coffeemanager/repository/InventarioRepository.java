package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository
        extends JpaRepository<Inventario, Integer> {

    boolean existsByIdProducto(Integer idProducto);

    boolean existsByIdProductoAndIdInventarioNot(
            Integer idProducto,
            Integer idInventario
    );

    Optional<Inventario> findByIdProducto(Integer idProducto);
}