package com.coffeemanager.coffeemanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class NotificacionRequestDTO {

    @NotNull(message = "El usuario es obligatorio")
    private Integer idUsuario;

    @NotBlank(message = "El título es obligatorio")
    @Size(
            max = 150,
            message = "El título no puede superar los 150 caracteres"
    )
    private String titulo;

    @NotBlank(message = "El mensaje es obligatorio")
    @Size(
            max = 500,
            message = "El mensaje no puede superar los 500 caracteres"
    )
    private String mensaje;

    @NotBlank(message = "El tipo es obligatorio")
    @Size(
            max = 50,
            message = "El tipo no puede superar los 50 caracteres"
    )
    private String tipo;

    private Boolean leida;

    private LocalDateTime fechaCreacion;

    public NotificacionRequestDTO() {
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