package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.Egreso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EgresoRepository
        extends JpaRepository<Egreso, Integer> {
}