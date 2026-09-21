package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermisoRepository extends JpaRepository<Permiso, Long> {

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdPermisoNot(String nombre, Long idPermiso);
}