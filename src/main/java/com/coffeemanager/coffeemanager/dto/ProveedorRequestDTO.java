package com.coffeemanager.coffeemanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProveedorRequestDTO {

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    @Size(max = 150, message = "El nombre no puede superar los 150 caracteres")
    private String nombre;

    @NotBlank(message = "La identificación del proveedor es obligatoria")
    @Size(max = 50, message = "La identificación no puede superar los 50 caracteres")
    private String identificacion;

    @Size(max = 30, message = "El teléfono no puede superar los 30 caracteres")
    private String telefono;

    @Email(message = "El correo electrónico no tiene un formato válido")
    @Size(max = 255, message = "El correo no puede superar los 255 caracteres")
    private String email;

    @Size(max = 255, message = "La dirección no puede superar los 255 caracteres")
    private String direccion;

    @Size(max = 20, message = "El estado no puede superar los 20 caracteres")
    private String estado;

    public ProveedorRequestDTO() {
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