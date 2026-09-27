package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoInventarioRepository
        extends JpaRepository<MovimientoInventario, Integer> {

    List<MovimientoInventario> findByIdProducto(Integer idProducto);

    List<MovimientoInventario> findByIdUsuario(Integer idUsuario);
}