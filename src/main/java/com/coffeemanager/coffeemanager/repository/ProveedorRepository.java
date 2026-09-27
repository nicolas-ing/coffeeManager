package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository
        extends JpaRepository<Proveedor, Integer> {

    boolean existsByIdentificacion(String identificacion);

    boolean existsByIdentificacionAndIdProveedorNot(
            String identificacion,
            Integer idProveedor
    );
}