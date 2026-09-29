package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.CuentaPorPagar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaPorPagarRepository
        extends JpaRepository<CuentaPorPagar, Integer> {

    List<CuentaPorPagar> findByIdCompra(Integer idCompra);
}