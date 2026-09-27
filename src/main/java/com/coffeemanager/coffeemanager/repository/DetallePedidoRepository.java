package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetallePedidoRepository
        extends JpaRepository<DetallePedido, Integer> {

    List<DetallePedido> findByIdPedido(Integer idPedido);
}