package com.coffeemanager.coffeemanager.security;

import com.coffeemanager.coffeemanager.entity.Permiso;
import com.coffeemanager.coffeemanager.entity.Rol;
import com.coffeemanager.coffeemanager.entity.Usuario;
import com.coffeemanager.coffeemanager.repository.PermisoRepository;
import com.coffeemanager.coffeemanager.repository.RolRepository;
import com.coffeemanager.coffeemanager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PermisoRepository permisoRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado: " + email
                ));

        Rol rol = rolRepository.findById(usuario.getIdRol().longValue())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Rol del usuario no encontrado"
                ));

        List<Permiso> permisos =
                permisoRepository.findByRolId(usuario.getIdRol());


        List<String> autoridades = new ArrayList<>();

        autoridades.add("ROLE_" + rol.getNombre());

        autoridades.addAll(
                permisos.stream()
                        .map(Permiso::getNombre)
                        .toList()
        );

        return User
                .withUsername(usuario.getEmail())
                .password(usuario.getPassword())
                .authorities(autoridades.toArray(new String[0]))
                .build();
    }
}