package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngresoRepository
        extends JpaRepository<Ingreso, Integer> {
}