package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.RolPermiso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolPermisoRepository extends JpaRepository<RolPermiso, Integer> {

    boolean existsByIdRolAndIdPermiso(Integer idRol, Integer idPermiso);
}