package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository
        extends JpaRepository<Producto, Integer> {

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdProductoNot(
            String codigo,
            Integer idProducto
    );
}