package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionRepository
        extends JpaRepository<Notificacion, Integer> {

    List<Notificacion> findByIdUsuario(Integer idUsuario);
}