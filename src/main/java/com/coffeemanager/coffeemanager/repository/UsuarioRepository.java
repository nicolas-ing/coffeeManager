package com.coffeemanager.coffeemanager.repository;

import com.coffeemanager.coffeemanager.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdUsuarioNot(String email, Integer idUsuario);

    Optional<Usuario> findByEmail(String email);
}