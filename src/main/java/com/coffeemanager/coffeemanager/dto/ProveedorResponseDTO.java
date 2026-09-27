package com.coffeemanager.coffeemanager.dto;

public class ProveedorResponseDTO {

    private Integer idProveedor;
    private String nombre;
    private String identificacion;
    private String telefono;
    private String email;
    private String direccion;
    private String estado;

    public ProveedorResponseDTO() {
    }

    public ProveedorResponseDTO(
            Integer idProveedor,
            String nombre,
            String identificacion,
            String telefono,
            String email,
            String direccion,
            String estado) {

        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.estado = estado;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}