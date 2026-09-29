package com.coffeemanager.coffeemanager.controller;

import com.coffeemanager.coffeemanager.dto.NotificacionRequestDTO;
import com.coffeemanager.coffeemanager.dto.NotificacionResponseDTO;
import com.coffeemanager.coffeemanager.service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> crearNotificacion(
            @Valid @RequestBody NotificacionRequestDTO request) {

        NotificacionResponseDTO respuesta =
                notificacionService.crearNotificacion(request);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<NotificacionResponseDTO>>
    listarNotificaciones() {

        List<NotificacionResponseDTO> respuesta =
                notificacionService.listarNotificaciones();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO>
    obtenerNotificacion(@PathVariable Integer id) {

        NotificacionResponseDTO respuesta =
                notificacionService.obtenerNotificacion(id);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<NotificacionResponseDTO>>
    listarPorUsuario(@PathVariable Integer idUsuario) {

        List<NotificacionResponseDTO> respuesta =
                notificacionService.listarPorUsuario(idUsuario);

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO>
    actualizarNotificacion(
            @PathVariable Integer id,
            @Valid @RequestBody NotificacionRequestDTO request) {

        NotificacionResponseDTO respuesta =
                notificacionService.actualizarNotificacion(
                        id,
                        request);

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(
            @PathVariable Integer id) {

        notificacionService.eliminarNotificacion(id);

        return ResponseEntity.noContent().build();
    }
}