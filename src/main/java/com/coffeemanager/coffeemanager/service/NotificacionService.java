package com.coffeemanager.coffeemanager.service;

import com.coffeemanager.coffeemanager.dto.NotificacionRequestDTO;
import com.coffeemanager.coffeemanager.dto.NotificacionResponseDTO;
import com.coffeemanager.coffeemanager.entity.Notificacion;
import com.coffeemanager.coffeemanager.exception.ResourceNotFoundException;
import com.coffeemanager.coffeemanager.repository.NotificacionRepository;
import com.coffeemanager.coffeemanager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public NotificacionResponseDTO crearNotificacion(
            NotificacionRequestDTO request) {

        validarUsuario(request.getIdUsuario());

        Notificacion notificacion = new Notificacion();

        notificacion.setIdUsuario(request.getIdUsuario());
        notificacion.setTitulo(request.getTitulo());
        notificacion.setMensaje(request.getMensaje());
        notificacion.setTipo(request.getTipo());

        if (request.getLeida() == null) {
            notificacion.setLeida(false);
        } else {
            notificacion.setLeida(request.getLeida());
        }

        if (request.getFechaCreacion() == null) {
            notificacion.setFechaCreacion(LocalDateTime.now());
        } else {
            notificacion.setFechaCreacion(
                    request.getFechaCreacion());
        }

        Notificacion guardada =
                notificacionRepository.save(notificacion);

        return convertirAResponseDTO(guardada);
    }

    public List<NotificacionResponseDTO> listarNotificaciones() {

        return notificacionRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public NotificacionResponseDTO obtenerNotificacion(Integer id) {

        Notificacion notificacion =
                notificacionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "La notificación con ID "
                                                + id
                                                + " no fue encontrada"));

        return convertirAResponseDTO(notificacion);
    }

    public List<NotificacionResponseDTO> listarPorUsuario(
            Integer idUsuario) {

        validarUsuario(idUsuario);

        return notificacionRepository.findByIdUsuario(idUsuario)
                .stream()
                .map(this::convertirAResponseDTO)
                .toList();
    }

    public NotificacionResponseDTO actualizarNotificacion(
            Integer id,
            NotificacionRequestDTO request) {

        Notificacion notificacion =
                notificacionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "La notificación con ID "
                                                + id
                                                + " no fue encontrada"));

        validarUsuario(request.getIdUsuario());

        notificacion.setIdUsuario(request.getIdUsuario());
        notificacion.setTitulo(request.getTitulo());
        notificacion.setMensaje(request.getMensaje());
        notificacion.setTipo(request.getTipo());

        if (request.getLeida() == null) {
            notificacion.setLeida(
                    notificacion.getLeida());
        } else {
            notificacion.setLeida(request.getLeida());
        }

        if (request.getFechaCreacion() == null) {
            notificacion.setFechaCreacion(
                    notificacion.getFechaCreacion());
        } else {
            notificacion.setFechaCreacion(
                    request.getFechaCreacion());
        }

        Notificacion actualizada =
                notificacionRepository.save(notificacion);

        return convertirAResponseDTO(actualizada);
    }

    public void eliminarNotificacion(Integer id) {

        Notificacion notificacion =
                notificacionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "La notificación con ID "
                                                + id
                                                + " no fue encontrada"));

        notificacionRepository.delete(notificacion);
    }

    private void validarUsuario(Integer idUsuario) {

        if (!usuarioRepository.existsById(idUsuario)) {
            throw new ResourceNotFoundException(
                    "El usuario con ID "
                            + idUsuario
                            + " no fue encontrado");
        }
    }

    private NotificacionResponseDTO convertirAResponseDTO(
            Notificacion notificacion) {

        return new NotificacionResponseDTO(
                notificacion.getIdNotificacion(),
                notificacion.getIdUsuario(),
                notificacion.getTitulo(),
                notificacion.getMensaje(),
                notificacion.getTipo(),
                notificacion.getLeida(),
                notificacion.getFechaCreacion()
        );
    }
}