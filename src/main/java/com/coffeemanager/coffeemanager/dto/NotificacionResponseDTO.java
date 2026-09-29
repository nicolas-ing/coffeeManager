package com.coffeemanager.coffeemanager.dto;

import java.time.LocalDateTime;

public class NotificacionResponseDTO {

    private Integer idNotificacion;
    private Integer idUsuario;
    private String titulo;
    private String mensaje;
    private String tipo;
    private Boolean leida;
    private LocalDateTime fechaCreacion;

    public NotificacionResponseDTO() {
    }

    public NotificacionResponseDTO(
            Integer idNotificacion,
            Integer idUsuario,
            String titulo,
            String mensaje,
            String tipo,
            Boolean leida,
            LocalDateTime fechaCreacion) {

        this.idNotificacion = idNotificacion;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.leida = leida;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getLeida() {
        return leida;
    }

    public void setLeida(Boolean leida) {
        this.leida = leida;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}