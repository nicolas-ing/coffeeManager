package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository
        extends JpaRepository<Compra, Integer> {

    boolean existsByNumeroDocumento(String numeroDocumento);

    boolean existsByNumeroDocumentoAndIdCompraNot(
            String numeroDocumento,
            Integer idCompra
    );
}