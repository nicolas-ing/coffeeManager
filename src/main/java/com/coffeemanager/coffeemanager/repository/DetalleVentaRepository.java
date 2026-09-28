package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentaRepository
        extends JpaRepository<DetalleVenta, Integer> {

    List<DetalleVenta> findByIdVenta(Integer idVenta);
}