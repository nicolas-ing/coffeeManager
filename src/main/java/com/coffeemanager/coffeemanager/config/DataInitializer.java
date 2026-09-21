package com.coffeemanager.coffeemanager.config;

import com.coffeemanager.coffeemanager.dto.RolRequestDTO;
import com.coffeemanager.coffeemanager.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RolService rolService;

    @Override
    public void run(String... args) {

        String nombreRol = "ADMINISTRADOR";

        if (!rolService.existePorNombre(nombreRol)) {

            RolRequestDTO request = new RolRequestDTO();

            request.setNombre(nombreRol);
            request.setDescripcion("Rol con acceso administrativo al sistema.");

            rolService.guardarRol(request);

            System.out.println("Rol creado correctamente: " + nombreRol);

        } else {
            System.out.println("El rol ya existe: " + nombreRol);
        }
    }
}