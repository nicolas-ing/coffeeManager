package com.coffeemanager.coffeemanager.dto;

public class PermisoResponseDTO {

    private Long idPermiso;
    private String nombre;
    private String descripcion;
    private String modulo;

    public PermisoResponseDTO() {
    }

    public PermisoResponseDTO(
            Long idPermiso,
            String nombre,
            String descripcion,
            String modulo) {

        this.idPermiso = idPermiso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.modulo = modulo;
    }

    public Long getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Long idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }
}