package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.CuentaPorCobrar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaPorCobrarRepository
        extends JpaRepository<CuentaPorCobrar, Integer> {

    List<CuentaPorCobrar> findByIdCliente(Integer idCliente);

    List<CuentaPorCobrar> findByIdVenta(Integer idVenta);
}