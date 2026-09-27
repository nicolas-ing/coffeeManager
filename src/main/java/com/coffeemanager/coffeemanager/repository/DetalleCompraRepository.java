package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleCompraRepository
        extends JpaRepository<DetalleCompra, Integer> {

    List<DetalleCompra> findByIdCompra(Integer idCompra);
}