package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermisoRepository extends JpaRepository<Permiso, Long> {

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdPermisoNot(String nombre, Long idPermiso);

    @Query("""
            SELECT p
            FROM Permiso p
            JOIN RolPermiso rp ON rp.idPermiso = p.idPermiso
            WHERE rp.idRol = :idRol
            """)
    List<Permiso> findByRolId(@Param("idRol") Integer idRol);
}