package com.coffeemanager.coffeemanager.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductoRequestDTO {

    @NotNull(message = "La categoría es obligatoria")
    private Integer idCategoria;

    @NotBlank(message = "El código del producto es obligatorio")
    @Size(
            max = 50,
            message = "El código no puede superar los 50 caracteres"
    )
    private String codigo;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(
            max = 150,
            message = "El nombre no puede superar los 150 caracteres"
    )
    private String nombre;

    @Size(
            max = 255,
            message = "La descripción no puede superar los 255 caracteres"
    )
    private String descripcion;

    @Size(
            max = 100,
            message = "El tipo no puede superar los 100 caracteres"
    )
    private String tipo;

    @NotBlank(message = "La unidad de medida es obligatoria")
    @Size(
            max = 50,
            message = "La unidad de medida no puede superar los 50 caracteres"
    )
    private String unidadMedida;

    @NotNull(message = "El precio de compra es obligatorio")
    @DecimalMin(
            value = "0.0",
            inclusive = false,
            message = "El precio de compra debe ser mayor que cero"
    )
    private BigDecimal precioCompra;

    @NotNull(message = "El precio de venta es obligatorio")
    @DecimalMin(
            value = "0.0",
            inclusive = false,
            message = "El precio de venta debe ser mayor que cero"
    )
    private BigDecimal precioVenta;

    @Size(
            max = 20,
            message = "El estado no puede superar los 20 caracteres"
    )
    private String estado;

    public ProductoRequestDTO() {
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}