package com.coffeemanager.coffeemanager.dto;

import jakarta.validation.constraints.NotNull;

public class RolPermisoRequestDTO {

    @NotNull(message = "El ID del rol es obligatorio")
    private Integer idRol;

    @NotNull(message = "El ID del permiso es obligatorio")
    private Integer idPermiso;

    public RolPermisoRequestDTO() {
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }
}