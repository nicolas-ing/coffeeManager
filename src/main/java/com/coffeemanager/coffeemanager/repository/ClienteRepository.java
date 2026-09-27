package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository
        extends JpaRepository<Cliente, Integer> {

    boolean existsByIdentificacion(String identificacion);

    boolean existsByIdentificacionAndIdClienteNot(
            String identificacion,
            Integer idCliente
    );
}