package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository
        extends JpaRepository<Venta, Integer> {

    boolean existsByNumeroDocumento(String numeroDocumento);

    boolean existsByNumeroDocumentoAndIdVentaNot(
            String numeroDocumento,
            Integer idVenta
    );
}