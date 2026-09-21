package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdRolNot(String nombre, Long idRol);
}